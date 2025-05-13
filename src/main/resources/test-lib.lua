#!lua name=mylib
local function city_by_cc(keys, args)
local match, cursor = {}, "0";
repeat
local ret = server.call("SCAN", cursor, "MATCH", "city:*", "COUNT", 100);
local cities = ret[2];
for i = 1, #cities do
local keyname = cities[i];
local ccode = server.call('HMGET',keyname,'Name','CountryCode')
if ccode[2] == args[1] then
match[#match + 1] = ccode[1];
end;
end;
cursor = ret[1];
until cursor == "0";
return match;
end
server.register_function('city_by_cc', city_by_cc)
