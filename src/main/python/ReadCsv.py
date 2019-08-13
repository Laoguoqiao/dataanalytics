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

    def LoadDataFrame(self, filename) :
        names = ['Date', 'Time', 'Open', 'High', 'Low', 'Close',
                 'Volume', 'Split Factor', 'Earnings', 'Dividends']
        if os.path.isfile(filename) :
            return pd.read_csv(filepath_or_buffer=filename, names=names)
        else :
            return pd.DataFrame()

    def LoadOriginData(self,symbol, start='2016-01-04', end='2016-03-25') :

        dfs = pd.DataFrame()

        filepath = self.root + 'OriginData\\allstocks_%s\\table_%s.csv'

        start_date = datetime.strptime(start, "%Y-%m-%d")
        end_date = datetime.strptime(end, "%Y-%m-%d")
        dt_range = end_date - start_date
        for i in range(0, dt_range.days + 1):
            dt = start_date + timedelta(i)
            filename = filepath % (dt.strftime("%Y%m%d"), symbol)
            df = LoadDataFrame(filename)

            if not df.empty :
                dfs = pd.concat([dfs, df])
        return dfs

    def get_data_by_symbol(self, symbol, slices='1', flag='min') :
        filename = self.root + 'ProcessedData\\' + symbol + '\\' + slices + (
            '' if flag == 'min' else '_' + flag) + '.csv'
        print(filename)

        if flag == 'min':
            df = pd.read_csv(filename)
            df['Time'] = df['Time'].apply(str).str.replace(':', '')
            df = ReIndex(df)
        else:
            df = pd.read_csv(filename, index_col=0)
        return df.T.to_json()

    def get_data_by_symbol_slice(self, symbol, flag, slices, MACD=False, RSI=False, KDJ=False) :
        data = self.get_data_by_symbol(symbol, slices=slices, flag=flag)
        if MACD:
            data = self.indicator.MACD(data)
        if RSI:
            data = self.indicator.RSI(data)
        if KDJ:
            data = self.indicator.KDJ(data)
        return data

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
    filepath = csvReader.root + 'ProcessedData/' + 'a' + '/30.csv'
    print(filepath)
    print(os.path.exists(filepath))
    df = pd.read_csv(filepath)
    print(df.head())
