local exists = redis.call('exists', KEYS[1])
if (exists == 0) then
    return 2
end

local ratelimit_info = redis.pcall('HMGET',KEYS[1], 'last_time', 'current_token', 'max_token', 'token_rate')
local last_time = ratelimit_info[1]
local current_token = tonumber(ratelimit_info[2])
local max_token = tonumber(ratelimit_info[3])
local token_rate = tonumber(ratelimit_info[4])
local time_info = redis.call('TIME')
local current_time = time_info[1] * 1000 + math.floor(time_info[2] / 1000)
local reverse_time = 1000 / token_rate
local past_time = current_time - last_time
local reverse_token = math.floor(past_time / reverse_time)

current_token = math.min(current_token + reverse_token, max_token)
last_time = reverse_time * reverse_token + last_time

local result = 0
if(current_token > 0) then
  result = 1
  current_token = current_token - 1
end
redis.call('HMSET',KEYS[1], 'last_time', last_time, 'current_token', current_token)
return result