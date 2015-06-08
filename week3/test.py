from Crypto.Hash import SHA256
from binascii import hexlify

hash = SHA256.new()
hash.update("message")
print len(hexlify(hash.digest()))