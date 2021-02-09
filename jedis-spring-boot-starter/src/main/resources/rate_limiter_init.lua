local exists = redis.call('exists', KEYS[1])
if (exists == 1) then
    return
end

local time_info = redis.call('TIME')
local current_time = time_info[1] * 1000 + math.floor(time_info[2] / 1000)
redis.call('HMSET', KEYS[1], 'last_time', current_time, 'current_token', ARGV[1], 'max_token', ARGV[1], 'token_rate', ARGV[2])