import json, requests, datetime
from flask import Flask, request, abort, jsonify
import requests
url = 'http://127.0.0.1:3000/'
texts = ["lightning eletric shock"]
# date_time = datetime.datetime.strptime('1/10/2018 21:45:00', '%d/%m/%Y %H:%M:%S').timestamp()
j_data = json.dumps({"txt":texts})
r = requests.post(url, data=j_data)

print(r, r.text)
