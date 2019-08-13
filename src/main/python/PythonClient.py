'''
* Service packages
'''
from flask import Flask
from flask import request
import json

'''
* Entities packages
'''
from Indicator import Indicator
from ReadCsv import ReadCsv

app = Flask(__name__)
indicator = Indicator()
csvReader = ReadCsv()


# @app.route('/analysisData', methods=['GET', 'POST'])
# def analysis_data():
#    return request.args.get("name")

@app.route('/analysisData', methods=['GET', 'POST'])
def analysis_data() :
    return request.args.get("name")


@app.route('/getDataBySymbol', methods=['GET', 'POST'])
def get_data_by_symbol() :
    # symbol = request.get_json()["symbol"]
    symbol = request.get_data()
    symbol = str(symbol, encoding='UTF-8')
    print(symbol)
    data = csvReader.get_data_by_symbol(symbol)
    return data


@app.route('/getDataBySymbolSlice', methods=['GET', 'POST'])
def get_data_by_symbol_slice() :
    # symbol = request.get_json()["symbol"]
    symbol = request.get_data()
    symbol = str(symbol, encoding='UTF-8')
    print(symbol)
    data = csvReader.get_data_by_symbol(symbol)
    return symbol

if __name__ == '__main__' :
    app.run(host='0.0.0.0')
