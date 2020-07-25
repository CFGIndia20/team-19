import os, sys, string, re
from flask import Flask, request
from pymessenger import Bot
from utils import wit_response


PAGE_ACCESS_TOKEN = 'EAARlZAihBuW8BAIdnMZA7o06YZAOmVtRiT8xpfRN4wgyapflpLenYKByan4LQH4Koze9vpQAYiXlHZBpbSnMIEZBNRXqY5cv7hpc9SZCpkwBXI2dTxFu8atrzBFbkwZA2w9nX7ck0MJPnRZAuQ30d6Hnvj0Wxs0Wc0v3L7uAba67TZCyEFEnyrdZCZC'
bot = Bot(PAGE_ACCESS_TOKEN)

app = Flask(__name__)

flag = 0
name=email=contact=city=state=picture=''
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
                    
                    

                    if flag == 2:
                        if "E-mail" not in messaging_text and "valid" not in messaging_text:
                            if(re.match("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", messaging_text) != None):
                                email = messaging_text
                                response = "please enter your branch.(for example, CSE/IT/EXTC)"
                                flag=3
                            else:
                                response = "please enter a valid email address"
                    
                    if flag==1:
                        if "name" not in messaging_text and "valid" not in messaging_text and "issue" not in messaging_text:
                            if all(c in string.ascii_letters + ' ' for c in messaging_text):
                                name = messaging_text
                                response = "please enter your E-mail address."
                                flag = 2
                            else:
                                response = "Please enter a valid name." 

                    if flag==0:
                        entity,value = wit_response(messaging_text)
                        hello,dump = wit_response(messaging_text)
                        print(entity)
                        print("hello")

                        if hello == "hello":
                            response = "Hey, how may i help you!"
                        elif entity == "complain:complain" and messaging_text!="Try asking, I want to register.":
                            response = "please type your full name."
                            flag =1
                            print(flag)
                            print("hello")
                        if response == None:
                            response = "Try asking, I want to report an issue."

                    bot.send_text_message(sender_id,response)
    return "OK", 200

def log(message):
    print(message)
    sys.stdout.flush()

if __name__ == "__main__":
    app.run(debug = True, port = 80)