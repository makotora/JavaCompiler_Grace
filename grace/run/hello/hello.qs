1: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 0

2: par,"Hello world!\n",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

3: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

4: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

