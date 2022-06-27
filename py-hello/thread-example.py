from threading import *
import time

s = Semaphore(4)


def example(nome, idade):
    for i in range(3):
        s.acquire()
        print("Olá", nome, idade)
        time.sleep(2)
        s.release()


tread1 = Thread(target=example, args=("João", 15))
tread2 = Thread(target=example, args=("José", 20))
tread3 = Thread(target=example, args=("Maria", 26))
tread4 = Thread(target=example, args=("Ana", 29))
tread1.start()
tread2.start()
tread3.start()
tread4.start()
