class TweetComplaint:
    def __init__(self, complain_text, city, state, tweet_id, username, image_url):

        self.complain_text = complain_text
        self.tweet_id = tweet_id
        self.username = username
        self.city = city
        self.state = state
        self.image_url = image_url
        self.status = "reported"

    def __str__(self):
        return self.complain_text +'\n'+ str(self.tweet_id) +'\n'+ self.username +'\n'+ self.city +'\n'+ self.state +'\n'+ self.image_url

    def to_dict(self):
        return {'category': '', 'city': self.city, 'state': self.state, 'image_url': self.image_url,
         'status': self.status, 'text': self.complain_text, 'tweet_id': self.tweet_id, 'username': self.username}