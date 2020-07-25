from flask import Flask, request, redirect, url_for, flash, jsonify
import numpy as np
import pickle as p
import json
import pandas as pd
import io
from sklearn.feature_extraction.text import TfidfVectorizer

app = Flask(__name__)
# app = Flask(__name__) = pickle.load(open('model.pkl','rb'))


df_category = pd.read_csv('cd_categories.csv')
df_category.head()

##global tfidf
##tfidf = TfidfVectorizer(sublinear_tf=True, min_df=5, norm='l2', encoding='latin-1', ngram_range=(1, 2), stop_words='english')


@app.route('/', methods = ['POST'])
def make_predict():
    #return jsonify(request.get_json())
    data = request.get_json(force=True)
    #return '123'
    #predict_request = [data['dept_date_time'],data['origin'],data['destination']]
    #predict_request = np.array(predict_request)
    #delay_predicted = model.predict(predict_request)
    output = prediction_text(data['txt'])
    #return '123'
    print(output)
    return jsonify(output)

def prediction_text(text):
##    texts = ["lightning eletric shock"]
    texts = text
    print('123')
    global tfidf
    text_features = tfidf.transform(texts)
    print('123')
    predictions = model.predict(text_features)
    print(predictions, "hello")
    for text, predicted in zip(texts, predictions):
        print('"{}"'.format(text))
        print("  - Predicted as: '{}'".format(df_category[df_category['id']==int(id_to_category[predicted])]['title'].values ))
        print("")
        result = df_category[df_category['id']==int(id_to_category[predicted])]['title'].values
    return str(result)

if __name__ == '__main__':
    modelfile = 'model.pkl'
    model = p.load(open(modelfile, 'rb'))
    tfidf = p.load(open("tfidf.pkl", "rb"))
    id_to_category = p.load(open("id_to_category.pkl", "rb"))
    app.run(debug=True, port=3000)
