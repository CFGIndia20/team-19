from flask import *
import os
from werkzeug.utils import secure_filename
import requests
import json, requests, datetime
from flask_cors import CORS

app = Flask(__name__)

app.config['SEND_FILE_MAX_AGE_DEFAULT'] = 0
app.secret_key = 'many random bytes'

cors = CORS(app)


@app.route('/', methods=['GET'])
def basic():
        return render_template('index.html')

@app.route('/predict', methods=['POST'])
def basic2():
        text = list(request.form.get('complain'))

        url = 'http://127.0.0.1:3000/'

        j_data = json.dumps({"txt":text})
        r = requests.post(url, data=j_data)

        result = "Prediction {}".format(r.text)

        result = str(result)

        return render_template('index.html', result=result)

if __name__ == '__main__':
	app.run()
