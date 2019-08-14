import pandas_datareader.data as web
import yfinance as fy
import numpy as np
from datetime import datetime, timedelta


class YahooSpyder:
    def getData(self, symbol, start, end):
        end = datetime.strptime(end, "%Y-%m-%d")
        end = end + timedelta(days=1)
        fy.pdr_override()
        data = web.get_data_yahoo(symbol, start, end)
        data.index = np.vectorize(lambda s: s.strftime('%Y-%m-%d'))(data.index.date)
        # data.set_index('Date')
        return data.T.to_json()

if __name__=='__main__':
    spyder = YahooSpyder()
    data = spyder.getData("googl", "2019-08-10", "2019-08-14")
    print(data)