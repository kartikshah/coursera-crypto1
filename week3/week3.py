from Crypto.Hash import SHA256
from binascii import hexlify

def compute_file_hash (filename):

    chunkSize = 1024

    fileChunks = []
    with open(filename, 'rb') as f:
        while True:
            chunk = f.read(chunkSize)
            if (len (chunk) == 0):
                break
            fileChunks.append(chunk)

    current_digest = ""
    for i, chunk in reversed(list(enumerate(fileChunks))):
        if i == (len(fileChunks) - 1):
            current_digest = SHA256.new(chunk).digest()
        else:
            current_digest = SHA256.new(chunk + current_digest).digest()

    print hexlify(current_digest)


compute_file_hash("./test.mp4")

#03c08f4ee0b576fe319338139c045c89c3e8e9409633bea29442e21425006ea8
#03c08f4ee0b576fe319338139c045c89c3e8e9409633bea29442e21425006ea8