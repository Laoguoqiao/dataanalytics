# -*- coding: utf-8 -*-
'''
* Data analysis packages
'''
import pandas as pd
import numpy as np

from glob import glob
import os

from datetime import datetime
from datetime import timedelta
from Indicator import Indicator



class ReadCsv :
    def __init__(self,
                 root='C:/Users/宝是宝藏的宝/Desktop/learning/Trading_Strategy/DataAnalytics/Data Analytics/') :
        self.root = root
        self.indicator = Indicator()

    def ReIndex(self, df, is_origin=False) :

        if is_origin :
            # df['Date'] = df['Date'].map(str)
            df['Date'] = pd.to_datetime(df['Date'], format='%Y%m%d').dt.date.map(str)
            # df['Time'] = df['Time'].map(str)
            df['Time'] = pd.to_datetime(df['Time'], format='%H%M').dt.time.map(str)

        hier_index = list(df['Date']+" "+ df['Time'])

        # Reset the Index
        df.reset_index()
        df.index = hier_index

        # Drop unwanted columns
        df.pop('Date')
        df.pop('Time')

        df.index.names = ['Date'+" "+'Time']

        return df

    def LoadDataFrame(self, filename) :
        names = ['Date', 'Time', 'Open', 'High', 'Low', 'Close',
                 'Volume', 'Split Factor', 'Earnings', 'Dividends']
        if os.path.isfile(filename) :
            return pd.read_csv(filepath_or_buffer=filename, names=names)
        else :
            return pd.DataFrame()

    def LoadOriginData(self, symbol, start='2016-01-04', end='2016-03-25') :

        dfs = pd.DataFrame()

        filepath = self.root + 'OriginData\\allstocks_%s\\table_%s.csv'

        start_date = datetime.strptime(start, "%Y-%m-%d")
        end_date = datetime.strptime(end, "%Y-%m-%d")
        dt_range = end_date - start_date
        for i in range(0, dt_range.days + 1):
            dt = start_date + timedelta(i)
            filename = filepath % (dt.strftime("%Y%m%d"), symbol)
            df = self.LoadDataFrame(filename)

            if not df.empty :
                dfs = pd.concat([dfs, df])

        dfs = self.ReIndex(dfs, is_origin=True)

        return dfs.T.to_json()

    def get_other_data(self, flag='lastday'):
        filename = self.root + 'OtherData\\' + flag + '.csv'
        df = pd.read_csv(filename, index_col=0)
        return df.T.to_json()

    def get_data_by_symbol(self, symbol, slices='1', flag='min', start='', end='', json=True) :
        filename = self.root + 'ProcessedData\\' + symbol + '\\' + slices + (
            '' if flag == 'min' else '_' + flag) + '.csv'
        # print(filename)

        if flag == 'min':
            df = pd.read_csv(filename)
            df = df[(df['Date'] >= datetime.strptime(start, "%Y-%m-%d").strftime("%Y-%m-%d") )&
                    (df['Date'] <= datetime.strptime(end, "%Y-%m-%d").strftime("%Y-%m-%d"))]
            df['Time'] = df['Time'].apply(str).str.replace(':', '')
            df = ReIndex(df)
        else:
            df = pd.read_csv(filename, index_col=0)
        if json :
            return df.T.to_json()
        else:
            return df

    def get_data_by_symbol_slice(self, symbol, flag, slices, start='', end='', MACD=False, RSI=False, KDJ=False) :
        data = self.get_data_by_symbol(symbol, slices=slices, flag=flag, start=start, end=end, json=False)
        if MACD:
            data = self.indicator.MACD(data)
        if RSI:
            data = self.indicator.RSI(data)
        if KDJ:
            data = self.indicator.KDJ(data)
        return data.T.to_json()

def ReIndex(df) :

    index = list(df['Date'] + " " + df['Time'])

    # Reset the Index
    df.reset_index()
    df.index = index

    # Drop unwanted columns
    df.pop('Date')
    df.pop('Time')

    df.index.names = ['Date Time']

    return df


if __name__ == '__main__' :
    csvReader = ReadCsv()
    indicator = Indicator()
    filepath = csvReader.root + 'OriginData/' + 'allstocks_20160104' + '/table_a.csv'
    print(filepath)
    print(os.path.exists(filepath))
    df = csvReader.LoadOriginData('a', '2016-01-04', '2016-01-05')
    # df = indicator.MACD(df)
    print(df)
