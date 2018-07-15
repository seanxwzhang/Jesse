clc
load(fullfile(pwd, 'data/companylist.mat'));
symbols = companylist.Symbol;

% fetch daily data for 5 years
% should run when market is closed
allData = containers.Map;
for i = 1:2
    now = datetime('now');
    sprintf('Retrieving data for %s', symbols(i));
    data = arrayfun(@(y) getDailyDataOneYear(symbols(i), now, y), 0:4, 'UniformOutput', 0);
    allData(char(symbols(i))) = horzcat(data{:});
end 


function [datum] = getDailyDataOneYear(symbol, now, yearDelta)
	t = now;
    t.Year = t.Year - yearDelta;
    datetimestr = datestr(t, 'yyyymmdd HH:MM:SS');
    datum = IBMatlab('action', 'history', 'symbol', symbol, 'barSize', '1 day', 'useRTH', 1, 'durationValue', 365, 'endDateTime', datetimestr);
end

