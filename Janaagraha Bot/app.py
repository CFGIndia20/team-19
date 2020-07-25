import os, sys
from flask import Flask, request
from pymessenger import Bot

PAGE_ACCESS_TOKEN = 'EAARlZAihBuW8BAIdnMZA7o06YZAOmVtRiT8xpfRN4wgyapflpLenYKByan4LQH4Koze9vpQAYiXlHZBpbSnMIEZBNRXqY5cv7hpc9SZCpkwBXI2dTxFu8atrzBFbkwZA2w9nX7ck0MJPnRZAuQ30d6Hnvj0Wxs0Wc0v3L7uAba67TZCyEFEnyrdZCZC'
bot = Bot(PAGE_ACCESS_TOKEN)

app = Flask(__name__)

flag = 0

@app.route('/', methods = ['GET'])
def verify():
    if request.args.get("hub.mode") == "subscribe" and request.args.get("hub.challenge"):
        if not request.args.get("hub.verify_token") == "hello":
            return "Verification token mismatch", 403
        return request.args["hub.challenge"], 200
    return "Hello world", 200


@app.route('/', methods=['POST'])
def webhook():
    global flag
    response=None
    data=request.get_json()
    log(data)
    if data['object']=='page':
        for entry in data['entry']:
            for messaging_event in entry['messaging']:
                sender_id = messaging_event['sender']['id']
                recipient_id = messaging_event['recipient']['id']
                if messaging_event.get('message'):
                    if 'text' in messaging_event['message']:
                        messaging_text = messaging_event['message']['text']
                    else:
                        messaging_text='no text'
                    if flag == 0:
                        response = messaging_text
                        bot.send_text_message(sender_id,response)
    return "OK", 200

def log(message):
    print(message)
    sys.stdout.flush()

if __name__ == "__main__":
    app.run(debug = True, port = 80)