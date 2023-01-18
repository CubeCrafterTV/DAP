.data
  n: .word 58
.text 
.globl main

main:
lw x11, n              # Lade Wert für n in Register x11 (Zählregister)
li x5, -1               # initialisiere das Ergebnisregister 
li x6, 3              # Lade Abbruchbedingung für die Sonderfälle in x6
    # Hier sollte ihr Code stehen

# Behandlung der Zahlen 0 - 2 
sonderfaelle: 
blt x11, x0, ende          # Abbruch wenn n erreicht wurde
addi x11,x11,-1            # Zählregister anpassen
addi x6,x6,-1
            # Zählregister für Sonderfälle (n < 3) anpassen 
addi x5,x5,1                # Ergebnisregister erhöhen (für 0 - 2) 
### Ausgabe f(n) für 0 - 2(nur für Aufgabenteil b relevant)
add x10, x5, x0     # Wert für die Ausgabe vorbereiten (Pseudo-op: mv) 
li x17, 36          # Syscall zur Ausgabe eines vorzeichenlosen Integers 
ecall               # Syscall ausführen
addi x10, x0, 0xA   # Lade das Zeichen für den Zeilenumbruch 
li x17, 11          # Syscall zur Ausgabe eines Zeichens laden
ecall               # Syscall ausführen
### Ende der Ausgabe
bne x6,x0, sonderfaelle     # Sprung zum Anfang der Schleife solange n < 3


schleife:
    # Hier sollte ihr Code stehen




ende:
    # Hier sollte ihr Code stehen
