'''
* Service packages
'''
from flask import Flask
from flask import request

'''
* Entities packages
'''
from Indicator import Indicator
from ReadCsv import ReadCsv
from YahooSpyder import YahooSpyder

app = Flask(__name__)
indicator = Indicator()
csvReader = ReadCsv()
spyder = YahooSpyder()


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

@app.route('/getChartHtml', methods=['GET', 'POST'])
def get_ChartHtml():
    directory = request.get_json()
    # directory = eval(directory)
    # print(directory)
    data = csvReader.get_chart_html(symbol=directory['symbol'], flag=directory['flag'],
                                    slices=directory['slices'], start=directory['start'],
                                    end=directory['end'], RSI=eval(directory['RSI']),
                                    MACD=eval(directory['MACD']), KDJ=eval(directory['KDJ']))
    return data


@app.route('/getOtherData', methods=['GET', 'POST'])
def get_other_data():
    directory = request.get_json()

    data = csvReader.get_other_data(flag=directory['flag'])
    return data


@app.route('/getDataByDict', methods=['GET', 'POST'])
def get_data_by_dict() :
    # symbol = request.get_json()["symbol"]
    directory = request.get_json()

    data = csvReader.get_data_by_symbol_slice(symbol=directory['symbol'], flag=directory['flag'],
                                              slices=directory['slices'], start=directory['start'],
                                              end=directory['end'], RSI=eval(directory['RSI']),
                                              MACD=eval(directory['MACD']), KDJ=eval(directory['KDJ']))
    label = eval(data)
    print(label)

    return data


if __name__ == '__main__' :
    app.run(host='0.0.0.0')
