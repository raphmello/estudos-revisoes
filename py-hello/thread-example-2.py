import _thread
import time

num_thread = 0
max_loop = 5
thread_iniciada = False

def atividade(nome_atividade, espera):
    global num_thread, max_loop, thread_iniciada
    thread_iniciada = True
    num_thread += 1
    contador = 0
    while contador < max_loop:
        time.sleep(espera)
        print("Thread :  %s" % (nome_atividade))
        contador += 1
    num_thread -= 1


_thread.start_new_thread(atividade, ("Tarefa 1", 1))
_thread.start_new_thread(atividade, ("Tarefa 2", 2))

while not thread_iniciada:
    pass
while num_thread > 0:
    pass
