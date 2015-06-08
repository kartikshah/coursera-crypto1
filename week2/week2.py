from Crypto.Cipher import AES
from binascii import hexlify, unhexlify
from Crypto import Random

def strxor(a, b):     # xor two strings of different lengths
    if len(a) > len(b):
        return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a[:len(b)], b)])
    else:
        return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a, b[:len(a)])])


def decrypt_cbc(key, iv, ciphertext):

    blocks = [ciphertext[z:z + 32] for z in range(0, len(ciphertext), 32)]
    cipher = AES.new(unhexlify(key))
    currentiv = iv
    m = []
    for block in blocks:
        partialresult = cipher.decrypt(unhexlify(block))
        result = strxor(unhexlify(currentiv), partialresult)
        # print result
        m.append(result)
        currentiv = block
    return "".join(z for z in m)

def decrypt_ctr(key, text):

    nonce = text[0:32]
    # counter = "00000000000000000000000000000000"
    ciphertext = text[32:]

    blocks = [ciphertext[z:z + 32] for z in range(0, len(ciphertext), 32)]
    # print blocks
    cipher = AES.new(unhexlify(key))
    m = []
    currentiv = nonce # + counter
    for block in blocks:
        partialresult = cipher.encrypt(unhexlify(currentiv))
        # print partialresult
        result = strxor(unhexlify(block), partialresult)
        # print result
        m.append(result)
        # counter = "{0:#0{1}x}".format(int(counter, 16) + 1, 34)
        # print counter[2:]
        currentiv = "{0:#0{1}x}".format(int(currentiv, 16) + 1, 34)[2:]   #+ counter[2:]
        # print currentiv
    return "".join(z for z in m)

def question1():
    key = "140b41b22a29beb4061bda66b6747e14"
    iv = "4ca00ff4c898d61e1edbf1800618fb28"
    ciphertext = "28a226d160dad07883d04e008a7897ee2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81"
    print decrypt_cbc(key, iv, ciphertext)


def question2():
    key2 = "140b41b22a29beb4061bda66b6747e14"
    iv2 = "5b68629feb8606f9a6667670b75b38a5"
    ciphertext2 = "b4832d0f26e1ab7da33249de7d4afc48e713ac646ace36e872ad5fb8a512428a6e21364b0c374df45503473c5242a253"
    print decrypt_cbc(key2, iv2, ciphertext2)

def question3():
    key2 = "36f18357be4dbd77f050515c73fcf9f2"
    ciphertext2 = "69dda8455c7dd4254bf353b773304eec0ec7702330098ce7f7520d1cbbb20fc388d1b0adb5054dbd7370849dbf0b88d393f252e764f1f5f7ad97ef79d59ce29f5f51eeca32eabedd9afa932911111111"
    print decrypt_ctr(key2, ciphertext2)

def question4():
    key2 = "36f18357be4dbd77f050515c73fcf9f2"
    ciphertext2 = "770b80259ec33beb2561358a9f2dc617e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c344511111"
    print decrypt_ctr(key2, ciphertext2)


question1()
question2()
question3()
question4()

# 93f252e764f1f5f7ad97ef79d59ce29f
# 5f51eeca32eabedd9afa932911111111

# nonce = "69dda8455c7dd4254bf353b773304eed"
# print "{0:#0{1}x}".format(int(nonce, 16) + 1, 34)[2:]