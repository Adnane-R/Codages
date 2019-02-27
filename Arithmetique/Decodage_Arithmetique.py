print("hello world")

thisList=["a","b","c","d","e","f"]
thisProba=[0,0.1,0.2,0.3,0.5,0.9,1]


# Code : 0.15633504384000005

r=(float) (input("Veuillez donner le code à décoder :"))
print("Le code saisi est : ", r)


i=0
taille=1
while r>0.0001:
    i=0
    while r-thisProba[i+1]>0 :
        i=i+1
    C=thisList[i]
    print(C)
    x=thisProba[i]
    y=thisProba[i+1]
    taille=y-x
    r=r-x
    r=r/taille
    print("Taille = " + str(taille) + "\t\tr= " + str(r))
    print("X = " + str(x) + "\t\ty= " + str(y))
    


