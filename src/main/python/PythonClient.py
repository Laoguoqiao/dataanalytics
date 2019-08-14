'''
* Service packages
'''
from flask import Flask
from flask import request
import json

from pyecharts import Line

'''
* Entities packages
'''
from Indicator import Indicator
from ReadCsv import ReadCsv
import datetime
from YahooSpyder import YahooSpyder

app = Flask(__name__)
indicator = Indicator()
csvReader = ReadCsv()
spyder = YahooSpyder()


def str2date(str,date_format="%Y-%m-%d"):
    date = datetime.datetime.strptime(str, date_format)
    return date.date()

# @app.route('/analysisData', methods=['GET', 'POST'])
# def analysis_data():
#    return request.args.get("name")


@app.route('/getYahooData', methods=['GET', 'POST'])
def get_yahoo_data():
    directory = request.get_json()
    # directory = eval(directory)
    # print(directory)
    data = spyder.getData(symbol=directory['symbol'],
                          start=directory['start'],
                          end=directory['end'])
    return data


@app.route('/getOriginData', methods=['GET', 'POST'])
def get_origin_data():
    directory = request.get_json()
    # directory = eval(directory)
    # print(directory)
    data = csvReader.LoadOriginData(symbol=directory['symbol'], start=directory['start'],
                                              end=directory['end'])
    return data


# @app.route('/analysisData', methods=['GET', 'POST'])
# def analysis_data() :
#     return request.args.get("name")
#
#
# @app.route('/getDataBySymbol', methods=['GET', 'POST'])
# def get_data_by_symbol() :
#     # symbol = request.get_json()["symbol"]
#     symbol = request.get_data()
#     symbol = eval(symbol)
#     data = csvReader.get_data_by_symbol(symbol)
#     return data
#
#
# @app.route('/getDataBySymbolSlice', methods=['GET', 'POST'])
# def get_data_by_symbol_slice() :
#     # symbol = request.get_json()["symbol"]
#     directory = request.get_json()
#     print(directory)
#     # directory = eval(directory)
#     # print(directory)
#     data = csvReader.get_data_by_symbol_slice(symbol=directory['symbol'], flag=directory['flag'],
#                                               slices=directory['slices'], RSI=eval(directory['RSI']),
#                                               MACD=eval(directory['MACD']), KDJ=eval(directory['KDJ']))
#     label = eval(data)
#     print(label)
#
#     return data

@app.route('/getOtherData', methods=['GET', 'POST'])
def get_other_data():
    directory = request.get_json()

    data = csvReader.get_other_data(flag=directory['flag'])
    return data

@app.route('/getDataByDict', methods=['GET', 'POST'])
def get_data_by_dict() :
    # symbol = request.get_json()["symbol"]
    directory = request.get_json()
    print(directory)
    #directory = eval(directory)
    #print(directory)
    data = csvReader.get_data_by_symbol_slice(symbol=directory['symbol'], flag=directory['flag'],
                                              slices=directory['slices'], start=directory['start'],
                                              end=directory['end'], RSI=eval(directory['RSI']),
                                              MACD=eval(directory['MACD']), KDJ=eval(directory['KDJ']))
    label = eval(data)
    print(label)

    return data


def render_data(symbol, data):
    label = eval(data)
    bar = Line(title=symbol)
    Date = []
    for key in label.keys() :
        Date.append(str2date(key))
    Open, High, Low, Close, Volume, Split, Earning, Dividends = [], [], [], [], [], [], [], []
    if symbol[1] != 'day':
        Time = []
    for values in label.values() :
        Open.append(values['Open'])
        High.append(values['High'])
        Low.append(values['Low'])
        Close.append(values['Close'])
        Volume.append(values['Volume'])
        Split.append(values['Split Factor'])
        Earning.append(values['Earnings'])
        Dividends.append(values['Dividends'])
        if symbol[1] != 'day' :
            Time.append(values['Times'])

    bar.add(name='Open', x_axis=Date, y_axis=Open, is_fill=True, is_smooth=True)
    bar.add(name='High', x_axis=Date, y_axis=High, is_fill=True, is_smooth=True)
    bar.add(name='Low', x_axis=Date, y_axis=Low, is_fill=True, is_smooth=True)
    bar.add(name='Close', x_axis=Date, y_axis=Close, is_fill=True, is_smooth=True)
    bar.add(name='Volume', x_axis=Date, y_axis=Volume, is_fill=True, is_smooth=True)
    bar.add(name='Split', x_axis=Date, y_axis=Split, is_fill=True, is_smooth=True)
    bar.add(name='Earning', x_axis=Date, y_axis=Earning, is_fill=True, is_smooth=True)
    bar.add(name='Dividends', x_axis=Date, y_axis=Dividends, is_fill=True, is_smooth=True)

    bar.render('Render.html')
    print("Render Over!")

if __name__ == '__main__' :
    app.run(host='0.0.0.0')
