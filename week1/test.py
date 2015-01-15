a = "A (private-key)  encryption scheme states 3 algorithms, namely a procedure for generation"
b ="XYWQ"

print a.encode
print a.encode('hex').decode('hex')

alist = [a[i:i+2] for i in range(0, len(a), 2)]
blist = [b[i:i+2] for i in range(0, len(b), 2)]

print alist
print blist

for x, y in zip(alist, blist):
    print x + "\t" + y

