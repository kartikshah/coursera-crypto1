import urllib2
import sys
from binascii import hexlify, unhexlify

TARGET = 'http://crypto-class.appspot.com/po?er='
def query(q):
    target = TARGET + urllib2.quote(q)    # Create query URL
    req = urllib2.Request(target)         # Send HTTP request to server
    try:
        f = urllib2.urlopen(req)          # Wait for response
    except urllib2.HTTPError, e:
        print "We got: %d" % e.code       # Print response code
        if e.code == 404:
            return True # good padding
        return False # bad padding

def strxor(a, b):     # xor two strings of different lengths
    if len(a) > len(b):
        return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a[:len(b)], b)])
    else:
        return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a, b[:len(a)])])



def guess(inputCipherText, thisByte, index, inputBlocks, blockIndex, resultsofar, pad):

    ciphertext = list(inputCipherText)
    blocks = list(inputBlocks)

    for g in range(0, 255, 1):
        guessiterByte = format(g, '#04x')[2:]
        guessIterBytes = guessiterByte + resultsofar

        print "************** g = " + guessiterByte + " and Pad = " + pad + "******************"
        print "Guess + Result : " + guessIterBytes
        # print inputCipherText

        result1 = strxor(unhexlify(guessIterBytes), unhexlify(pad))
        # print "XORing : Result " + hexlify(result1) + "  and " + "".join(inputCipherText[index:])

        result2 = strxor(result1, unhexlify("".join(inputCipherText[index:])))
        # print "Result2: " + hexlify(result2)
        # print "Trying byte xor guess xor pad ... " + hexlify(result2)[:2]
        hexResult2 = hexlify(result2)

        result2List = [hexResult2[i: i + 2] for i in range(0, len(hexResult2), 2)]

        originalvalue = inputCipherText[index]
        # inputCipherText[index] = hexlify(result2)[:2]
        tempCipherText = inputCipherText[0:index] + result2List
        # print tempCipherText
        # print inputCipherText

        blocks[blockIndex] = "".join(tempCipherText)

        ciphertry = "".join(blocks[0:blockIndex+2])
        inputCipherText[index] = originalvalue
        print "Trying cipher : " + ciphertry
        if query(ciphertry):
           # print guessiterByte
           return guessiterByte

def guessouter():
    ciphertext = 'f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4'

    chunkSize = 32

    completeResult = ""

    cipherBlocks = [ciphertext[i:i + chunkSize] for i in range(0, len(ciphertext), chunkSize)]

    print "Cipher Blocks are : " + ", ".join(cipherBlocks)

    numberOfCipherBlocks = len(cipherBlocks)

    for element, thisBlock in enumerate(list(reversed(cipherBlocks))):
        numberOfCipherBlocks = numberOfCipherBlocks - 1
        if element == 0:
            #Skip first block, because it is not useful in Padding Attack
            continue

        print "**** Block : " + str(numberOfCipherBlocks) + " is thisBlock " + thisBlock

        cipherBlockInTwoBytes = [thisBlock[i: i + 2] for i in range(0, len(thisBlock), 2)]

        result = []

        iter = 1
        for index, thisByte in reversed(list(enumerate(cipherBlockInTwoBytes))):
            # print "index : " + str(index)
            pad = str(format(iter, '#04x')[2:]) * iter
            # print pad
            # print cipherBlockInTwoBytes
            # print thisByte
            # print index
            # print cipherBlocks
            # print numberOfCipherBlocks
            # print result
            # print "".join(reversed(result))
            resultbyte = guess(cipherBlockInTwoBytes, thisByte, index, cipherBlocks, numberOfCipherBlocks, "".join(reversed(result)), pad)
            if resultbyte:
                result.append(resultbyte)
            else:
                result.append('09')
            iter = iter + 1
        print "Result : " + unhexlify("".join(reversed(result)))
        completeResult = unhexlify("".join(reversed(result))) + completeResult
        print completeResult
        print "Kartik"
guessouter()

