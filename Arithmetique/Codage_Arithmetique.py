borneInf=0.0 
borneSup=1.0 
Alphabet = ["a", "b", "c", "d", "e", "f"]
Bornes=[0,0.1,0.2,0.3,0.5,0.9,1]


msg=input("Veuillez donner le message à coder :");
print("Le message saisi est : ", msg)



i=0

print("Caractere         Borne Inferieure         Borne Supérieure")

while i<len(msg):
       c=msg[i]
       x=Bornes[Alphabet.index(c)]
       i=i+1
       y=Bornes[Alphabet.index(c)+1]
       taille=borneSup-borneInf
       borneSup=borneInf+taille*y
       borneInf=borneInf+taille*x
       print(c + "         " + str(borneInf) + "        " + str(borneSup))