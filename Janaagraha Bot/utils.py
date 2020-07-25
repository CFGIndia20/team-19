from wit import Wit
access_token ="Q4O5DVG43UEH7CCKXNMSWC44M7UXMVDP"
client = Wit(access_token = access_token)

def wit_response(message_text):
    resp = client.message(message_text)
    print("Deepak")
    print(resp)
    print("Deepak")
    if resp['_text'] == "Hello" or resp['_text'] == "hello":
        return "hello","cric" 
    entity = None
    value= None
    try:
        entity=list(resp['entities'])[1]
        value =resp['entities'][entity][0]['value']
    except:
        pass
    return(entity,value)


