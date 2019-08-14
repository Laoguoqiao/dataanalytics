import pandas as pd


class Indicator :
    def __init__(self) :
        pass

    def MACD(self, dataframe, short=12, long=26, mid=9) :
        df = pd.DataFrame()

        # calculate the EMA
        df['S_EMA'] = pd.Series(dataframe['Close']).ewm(span=short).mean()
        df['L_EMA'] = pd.Series(dataframe['Close']).ewm(span=long).mean()

        # calculate the diff, fif = s_ema - l_ema
        dataframe['DIF'] = df['S_EMA'] - df['L_EMA']
        df['DIF_diff'] = dataframe['DIF'].diff()

        # fill nan with 0
        df.fillna(0, inplace=True)

        # DEA
        dataframe['DEA'] = pd.Series(dataframe['DIF']).ewm(span=mid).mean()
        df['DEA_diff'] = dataframe['DEA'].diff()

        # MACD = 2 * (dif - dea)
        dataframe['MACD'] = 2 * (dataframe['DIF'] - dataframe['DEA'])

        # fill nan with 0
        df.fillna(0, inplace=True)
        dataframe.fillna(0, inplace=True)

        return dataframe

    def RSI(self, dataframe, windows=9) :
        delta = dataframe.Close.diff()
        up_days = delta.copy()
        up_days[delta <= 0] = 0.0
        down_days = abs(delta.copy())
        down_days[delta > 0] = 0.0
        RS_up = up_days.rolling(windows).mean()
        RS_down = down_days.rolling(windows).mean()
        dataframe[('RSI' + str(windows))] = 100 - 100 / (1 + RS_up / RS_down)
        dataframe.fillna(0, inplace=True)
        return dataframe

    def KDJ(self, dataframe, windows=9) :
        low_list = dataframe['Low'].rolling(windows, min_periods=windows).min()
        low_list.fillna(value=dataframe['Low'].expanding().min(), inplace=True)
        high_list = dataframe['High'].rolling(windows, min_periods=windows).max()
        high_list.fillna(value=dataframe['High'].expanding().max(), inplace=True)
        rsv = (dataframe['Close'] - low_list) / (high_list - low_list) * 100

        dataframe['K'] = pd.DataFrame(rsv).ewm(com=2).mean()
        dataframe['D'] = dataframe['K'].ewm(com=2).mean()
        dataframe['J'] = 3 * dataframe['K'] - 2 * dataframe['D']

        dataframe.fillna(0, inplace=True)

        return dataframe
