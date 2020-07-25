from wit import Wit
access_token ="QAMZLDK4IU6Y2MCGWRYDZGXLFS3MTB5W"
client = Wit(access_token = access_token)

def wit_response(message_text):
    resp = client.message(message_text)
    print("Deepak")
    print(resp)
    print("Deepak")
    if resp['text'] == "Hello" or resp['text'] == "hello":
        return "hello", "cric"
    entity = None
    value= None
    try:
        entity=list(resp['entities'])[0]
        value =resp['entities'][entity][0]['value']
    except:
        pass
    return(entity,value)
