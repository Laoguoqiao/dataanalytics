import urllib
import re
import pandas as pd
import os


class Spyder :
    def __init__(self, url = 'https://finance.yahoo.com/'):
        self.url = url

    # 爬虫抓取网页函数
    def getHtml(self, url) :
        html = urllib.request.urlopen(url).read()
        html = html.decode('UTF-8')
        return html

    # 抓取网页股票代码函数
    def getStackCode(self, html) :
        s = r'<li><a target="_blank" href="http://quote.eastmoney.com/\S\S(.*?).html">'
        pat = re.compile(s)
        code = pat.findall(html)
        return code

    def getStackDataBlock(self, html):
        block_re = r'<table class="W(100%)" data-reactid="42">(.*?)</div>'
        pat = re.compile(block_re)
        block_html = pat.findall(html)
        return block_html

    def getHeader(self, datablock):
        symbol_re = r'<thead.*?><span data-.*?>(.*?)</span>'
        pat = re.compile(symbol_re)
        symbol = pat.findall(datablock)

        others_re = r'<th class.*?><span data.*?>(.*?)</span>'
        pat = re.compile(others_re)
        others = pat.findall(datablock)
        header = symbol + others
        return header

    def getData(self, datablock):
        dataline_re = r'<tr.*?>(.*?)</tr>'
        pat = re.compile(dataline_re)
        datalines = pat.findall(datablock)
        symbol_re = r'<a href.*?>(.*?)</a>'
        for dataline in datalines:


    def getStack(self):
        html = self.getHtml(self.url)
        datablock = self.getStackDataBlock(html)
        header = self.getHeader(datablock)
        data =
        code = self.getStackCode(self.getHtml(self.url))
        # 获取所有股票代码（以6开头的，应该是沪市数据）集合
        CodeList = []
        for item in code :
            if item[0] == '6' :
                CodeList.append(item)
                print(item)


if __name__ == '__main__':
    spyder = Spyder()
    spyder.getStack()
