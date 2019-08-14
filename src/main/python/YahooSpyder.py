import pandas as pd
import datetime
import pandas_datareader.data as web
import yfinance as fy

fy.pdr_override()
start = datetime.datetime(2016, 7, 1).date()
end = datetime.datetime(2016, 8, 1).date()
print(start)
data = {}
apple = web.get_data_yahoo("AAPL", start, end)

print(apple.head())