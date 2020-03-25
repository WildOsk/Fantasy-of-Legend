posicion= input('Posicion:')
kills=int(input('Numero de kills:'))
muertes=int(input('Numero de muertes:'))
minions=int(input('Numero de minions:'))
assist=int(input('Numero de assist:'))
if(posicion=supp):
    assist= assist*2
elif:
    assist= assist
if(posicion=top || posicion=mid || posicion=adc):
    minions= minions*0,01
elif(posicion=jng):
    minions= minions*0,015
elif(posicion=supp):

    minions= minions*0,02

total=kills*2-muertes+assist+minions
print(total)