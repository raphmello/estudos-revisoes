from multiprocessing import cpu_count

NAME = input("Qual seu nome?")
IDADE = input("Qual sua idade?")
print(NAME + " nasceu em " + str(2022 - int(IDADE)))

# s=Semaphore(counter)

print(cpu_count())
