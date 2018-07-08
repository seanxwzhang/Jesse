function [res, err] = fetchHistory(ticker, varargin)
%fetchHistory fetch historical ticker data 
% options/varargin:
%    'range':  
%        specify range of data requested, e.g., '1D', '2M', '3Y'
%        note: no more than '5Y' due to IB limitation
%    'barSize':  
%        Size of data bars to be returned (within
%         IB/TWS limits). Valid values include:
%         ? 1 sec, 5/10/15/30 secs
%         ? 1 min (default)
%         ? 2/3/5/10/15/20/30 mins
%         ? 1 hour, 2/3/4/8 hours
%         ? 1 day, 1 w, 1 m
%    'endDateTime':
%         'YYYYMMDD hh:mm:ss TMZ' format
%         (the TMZ time zone is optional51)
%    'useRTH':
%         [1, 0]
%    'fileName':
%         name of the file the data goes to


% check arguments
p = inputParser;
defaultRange = '1Y';
defaultBarSize = '1 day';
defaultEndDateTime = datestr(datetime('now'), 'yyyymmdd HH:MM:SS');
defaultFileName = '';
defaultUseRTH = 0;
checkUseRTH = @(x) ismember(x, [0,1]);

addRequired(p, 'ticker', @ischar);
addOptional(p, 'range', defaultRange, @ischar);
addOptional(p, 'barSize', defaultBarSize, @ischar);
addOptional(p, 'endDateTime', defaultEndDateTime, @ischar);
addOptional(p, 'useRTH', defaultUseRTH, checkUseRTH);
addOptional(p, 'fileName', defaultFileName, @ischar);

parse(p, ticker, varargin{:});

c = struct2cell(p.Results);
[barSize, endDateTime, fileName, range, ticker, useRTH] = c{:};
if ~ ~isempty(fileName)
    date_ = strsplit(endDateTime);
    fileName = strcat(ticker, '_', strrep(barSize, ' ', ''), '_bar_', range, '_to_', date_{1}, '_', num2str(useRTH));

rangeValue = str2num(range(1));
rangeUnit = range(2);

data = {}
if rangeUnit == 'Y' && rangeValue > 1 % pageing needed
    for i = 1:rangeValue
        datum = IBMatlab('action','history', 'symbol', ticker, 'barSize', barSize, 'useRTH', useRTH, 'durationValue', 366, 'endDateTime', '20080124 00:00:00')
    end
end


    
end

