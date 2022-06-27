import xmlrpc.client

with xmlrpc.client.ServerProxy("http://localhost:8050/") as proxy:
    print("4 é par: %s" % str(proxy.eh_par(4)))
    print("99 é par: %s" % str(proxy.eh_par(99)))