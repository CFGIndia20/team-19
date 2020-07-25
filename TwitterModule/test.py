import json
from twitter_helper import TwitterHelper

with open('config.json') as f:
	data = json.load(f)

username = "@CoolDude32149"
th = TwitterHelper(data, username)

message = "Thank you for your complaint"

th.stream_tweet()