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


class ReadCsv :
    def __init__(self,
                 root='C:/Users/宝是宝藏的宝/Desktop/learning/Trading_Strategy/DataAnalytics/Data Analytics/'):
        self.root = root

    def get_data_by_symbol(self, symbol) :
        filename = self.root + 'ProcessedData\\' + symbol + '\\*.csv'
        filenames = glob(filename)
        for name in filenames:
            print(name)
            df = pd.read_csv(name)
            return df.to_json()


def LoadDataFrame(filename) :
    names = ['Date', 'Time', 'Open', 'High', 'Low', 'Close',
             'Volume', 'Split Factor', 'Earnings', 'Dividends']
    if os.path.isfile(filename) :
        return pd.read_csv(filepath_or_buffer=filename, names=names)
    else :
        return pd.DataFrame()


def ReIndex(df, is_origin=True) :
    if is_origin :
        # df['Date'] = df['Date'].map(str)
        df['Date'] = pd.to_datetime(df['Date'], format='%Y%m%d')
        # df['Time'] = df['Time'].map(str)
        df['Time'] = pd.to_datetime(df['Time'], format='%H%M').dt.time

    tuples = list(zip(df['Date'], df['Time']))
    hier_index = pd.MultiIndex.from_tuples(tuples)

    # Reset the Index
    df.reset_index()
    df.index = hier_index

    # Drop unwanted columns
    df.pop('Date')
    df.pop('Time')

    df.index.names = ['Date', 'Time']

    return df


if __name__ == '__main__' :
    csvReader = ReadCsv()
    filepath = csvReader.root + 'ProcessedData/' + 'a' + '/30.csv'
    print(filepath)
    print(os.path.exists(filepath))
    df = pd.read_csv(filepath)
    print(df.head())
