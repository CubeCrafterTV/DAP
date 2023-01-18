.data
    n: .word 58  # input value
    .text
    .globl _start

_start:
    lw x10, n    # load input value to x10 register
    lw a1, 0x2
    bgt a0, a1, recursive   # if input value is greater than 2, jump to recursive
    j abbruchbedingung     # if input value is less or equal to 2, jump to abbruchbedingung
    j end

recursive:

abbruchbedingung:
	add t1,x1,x0 #retten cvon x1
	
	add s0,x10,x0 #Zahl berechnen
	jr x1 #Rückschreiben von x1
end:
    # program ends here, result is in s0
    	add x10, s0, x0     # Wert für die Ausgabe vorbereiten (Pseudo-op: mv) 
	li x17, 36          # Syscall zur Ausgabe eines vorzeichenlosen Integers 
	ecall               # Syscall ausführen
	addi x10, x0, 0xA   # Lade das Zeichen für den Zeilenumbruch 
	li x17, 11          # Syscall zur Ausgabe eines Zeichens laden
	ecall               # Syscall ausführen