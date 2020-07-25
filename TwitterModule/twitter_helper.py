from tweet_complaint import TweetComplaint
from firestore_helper import FirestoreHelper
import tweepy 
import json
import re

class MyStreamListener(tweepy.StreamListener):

	def __init__(self, api, username):
		super(MyStreamListener, self).__init__()
		self.api = api
		self.username = username
		self.fh = FirestoreHelper('firestore_creds.json')

	def reply_to_tweet(self, reply, tweetId):
		self.api.update_status(reply, in_reply_to_status_id=tweetId)

	def on_status(self, status):
		"""
		Callback method when tweet received
		"""
		
		text = status.text
		text = text.replace(self.username, '')
		text = re.sub(r"http\S+", "", text)
		text = text.strip()
		
		tweet_id = status.id
		username = status.user.name
		image_url = ""
		if 'media' in status.entities:
			for image in status.entities['media']:
				image_url = image['media_url']


		s1 = re.split("\n", text)
		complaint_text = re.split("desc-", s1[0])[1]

		location = re.split("loc-", s1[1])[1]

		city = re.split(",",location)[0].strip()
		state = re.split(",",location)[1].strip()

		tc = TweetComplaint(complaint_text, city, state, tweet_id, username, image_url)
		print(tc.to_dict())

		self.fh.push_complaint(tc.to_dict())
		
		self.api.update_status('@{} Thank you for registering the complaint!'.format(status.user.screen_name), in_reply_to_status_id=status.id_str)


class TwitterHelper:

	def __init__(self, keys, username):
	 
		# Authorization to consumer key and consumer secret 
		auth = tweepy.OAuthHandler(keys['consumer_key'], keys['consumer_secret']) 
	
		# Access to user's access key and access secret 
		auth.set_access_token(keys['access_key'], keys['access_secret']) 
	
		# Calling api 
		self.api = tweepy.API(auth)
		self.tweets_list = []
		self.tweets = []
		self.username = username
		self.myStreamListener = MyStreamListener(self.api, self.username)


	def stream_tweet(self):
		"""
		Live tweet listener, inputs tweets with @ mentions
		"""
		self.myStream = tweepy.Stream(auth = self.api.auth, listener=self.myStreamListener)
		self.myStream.filter(track=['@CoolDude32149'], is_async=True)


