'''
* Data analysis packages
'''
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
%matplotlib inline

from glob import glob
import os

from datetime import datetime
from datetime import timedelta
'''
* Service packages
'''
from flask import Flask
from flask import request
import json

app = Flask(__name__)

#@app.route('/analysisData', methods=['GET', 'POST'])
#def analysis_data():
#    return request.args.get("name")
def LoadDataFrame(filename):
    names = ['Date', 'Time', 'Open', 'High', 'Low', 'Close',
             'Volume', 'Split Factor', 'Earnings', 'Dividends']
    if os.path.isfile(filename):
        return pd.read_csv(filepath_or_buffer=filename, names=names)
    else:
        return pd.DataFrame()
def ReIndex(df, is_origin=True):

    if is_origin:
        #df['Date'] = df['Date'].map(str)
        df['Date'] = pd.to_datetime(df['Date'], format='%Y%m%d')
        #df['Time'] = df['Time'].map(str)
        df['Time'] = pd.to_datetime(df['Time'], format='%H%M').dt.time

    tuples = list(zip(df['Date'],df['Time']))
    hier_index = pd.MultiIndex.from_tuples(tuples)

    # Reset the Index
    df.reset_index()
    df.index = hier_index

    # Drop unwanted columns
    df.pop('Date')
    df.pop('Time')

    df.index.names = ['Date','Time']

    return df

@app.route('/analysisData', methods=['GET', 'POST'])
def analysis_data():
    return request.args.get("name")

@app.route('/getDataBySymbol', methods=['GET','POST'])
def get_data_by_symbol():
    symbol = request.get_data()
    return symbol

if __name__ == '__main__':
    app.run()