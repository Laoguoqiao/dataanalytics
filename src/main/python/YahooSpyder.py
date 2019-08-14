import pandas_datareader.data as web
import yfinance as fy

class YahooSpyder:
    def getData(self, symbol, start, end):
        fy.pdr_override()
        data = web.get_data_yahoo(symbol, start, end)
        #data.index = data.index.data.dt.date.map(str)
        # data.set_index('Date')
        return data#.T.to_json()

if __name__=='__main__':
    spyder = YahooSpyder()
    data = spyder.getData("googl", "2019-08-10", "2019-08-14")
    print(data.index.date.strp)