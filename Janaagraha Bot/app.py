import os, sys, string, re
from flask import Flask, request
from pymessenger import Bot
from utils import wit_response
from flask_sqlalchemy import SQLAlchemy
app = Flask(__name__)
PAGE_ACCESS_TOKEN = 'EAARlZAihBuW8BAIdnMZA7o06YZAOmVtRiT8xpfRN4wgyapflpLenYKByan4LQH4Koze9vpQAYiXlHZBpbSnMIEZBNRXqY5cv7hpc9SZCpkwBXI2dTxFu8atrzBFbkwZA2w9nX7ck0MJPnRZAuQ30d6Hnvj0Wxs0Wc0v3L7uAba67TZCyEFEnyrdZCZC'
bot = Bot(PAGE_ACCESS_TOKEN)
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///db.sqlite3'
db = SQLAlchemy(app)
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50))
    email = db.Column(db.String(50))
    contact = db.Column(db.String(10))
    city = db.Column(db.String(50))
    state = db.Column(db.String(50))
    description = db.Column(db.String(250))
    picture_1=db.Column(db.String(250))
    SENDER_ID = db.Column(db.String(20))



flag = 0
name=email=contact=city=state=description=picture1=''
@app.route('/', methods = ['GET'])
def verify():
    if request.args.get("hub.mode") == "subscribe" and request.args.get("hub.challenge"):
        if not request.args.get("hub.verify_token") == "hello":
            return "Verification token mismatch", 403
        return request.args["hub.challenge"], 200
    return "Hello world", 200


@app.route('/', methods=['POST'])
def webhook():
    global flag,name,email,city,state,contact,description,picture1
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
                    if 'attachments' in messaging_event['message']:
                        image_1=messaging_event['message']['attachments'][0]['payload']['url']
                        messaging_text = image_1

                    if flag == 7:
                        if "photos" not in messaging_text:
                            picture1=messaging_text
                            print(picture1)
                            data_stored(name,email,contact,city,state,description,picture1,sender_id)
                            response = "you have been registered. Thanks."
                            flag=8
                    if flag == 6:
                        if "description" not in messaging_text:
                            description = messaging_text
                            #data_stored(name,email,contact,city,state,sender_id)
                            response = "please share on-field photos"
                            flag = 7
                    
                    if flag == 5:
                        if "state" not in messaging_text  and "valid" not in messaging_text:
                            if all(c in string.ascii_letters + ' ' for c in messaging_text):
                                state = messaging_text
                                response = "please share description of the incident"
                                flag = 6
                            else:
                                response = "please enter a valid state name."

                    if flag == 4:
                        if "city" not in messaging_text  and "valid" not in messaging_text:
                            if all(c in string.ascii_letters + ' ' for c in messaging_text):
                                city = messaging_text
                                response = "please enter name of your state."
                                flag = 5
                            else:
                                response = "please enter a valid city name."
                    
                    if flag == 3:
                        if "contact" not in messaging_text and "valid" not in messaging_text:
                            if(re.match(r'[789]\d{9}$', messaging_text) != None):
                                contact = messaging_text
                                response = "please enter your city name."
                                flag=4
                            else:
                                response = "please enter a valid contact number."

                    if flag == 2:
                        if "E-mail" not in messaging_text and "valid" not in messaging_text:
                            if(re.match("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", messaging_text) != None):
                                email = messaging_text
                                response = "please enter your contact number."
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
                            flag=1
                            print(flag)
                            print("hello")
                        if response == None:
                            response = "Try asking, I want to report an issue."

                    bot.send_text_message(sender_id,response)
    return "OK", 200

def log(message):
    print(message)
    sys.stdout.flush()

def data_stored(name,email,contact,city,state,description,picture1,sender_id):
    user = User(name=name,email=email,contact=contact,city=city,state=state,description=description,picture_1=picture1,SENDER_ID=sender_id)
    db.session.add(user)
    db.session.commit()

if __name__ == "__main__":
    app.run(debug = True, port = 80)