import os
import json
i=1
list=[]
for line in open("./book_description.txt"):
    entry={}
    entry['id']=i
    entry['description']=line
    list.append(entry)
    i=i+1
with open("./book_description.json","w") as f:
    json.dump(list,f,ensure_ascii=False)