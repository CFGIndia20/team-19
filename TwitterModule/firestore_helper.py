from firebase_admin import credentials, firestore
import firebase_admin


class FirestoreHelper:
    def __init__(self, credentials_path):
        self.__cred = credentials.Certificate(credentials_path)
        self.default_app = firebase_admin.initialize_app(self.__cred)
        self.db = firestore.client()

    def push_complaint(self, data):
        self.db.collection('Complaints').document().set(data)