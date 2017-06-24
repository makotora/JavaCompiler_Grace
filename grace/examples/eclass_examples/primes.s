.intel_syntax noprefix
.text
	.global main
L1:
prime_2:
	push ebp
	mov ebp, esp
	sub esp, 28
L2:
	mov eax, DWORD PTR [ebp + 16]
	mov edx, 0
	cmp eax, edx
	jl L4
L3:
	jmp L11
L4:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 8], eax
L5:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L6:
	lea esi, DWORD PTR [ebp - 12]
	push esi
L7:
	push DWORD PTR [ebp + 8]
	call prime_2
	add esp, 12
L8:
	mov eax, DWORD PTR [ebp - 12]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L9:
	jmp prime_2_end
L10:
	jmp L41
L11:
	mov eax, DWORD PTR [ebp + 16]
	mov edx, 2
	cmp eax, edx
	jl L13
L12:
	jmp L16
L13:
	mov eax, 0
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L14:
	jmp prime_2_end
L15:
	jmp L41
L16:
	mov eax, DWORD PTR [ebp + 16]
	mov edx, 2
	cmp eax, edx
	je L18
L17:
	jmp L21
L18:
	mov eax, 1
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L19:
	jmp prime_2_end
L20:
	jmp L41
L21:
	mov eax, DWORD PTR [ebp + 16]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 16], edx
L22:
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 0
	cmp eax, edx
	je L24
L23:
	jmp L27
L24:
	mov eax, 0
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L25:
	jmp prime_2_end
L26:
	jmp L41
L27:
	mov eax, 3
	mov DWORD PTR [ebp - 4], eax
L28:
	mov eax, DWORD PTR [ebp + 16]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 20], eax
L29:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp - 20]
	cmp eax, edx
	jle L31
L30:
	jmp L39
L31:
	mov eax, DWORD PTR [ebp + 16]
	cdq
	mov ebx, DWORD PTR [ebp - 4]
	idiv ebx
	mov DWORD PTR [ebp - 24], edx
L32:
	mov eax, DWORD PTR [ebp - 24]
	mov edx, 0
	cmp eax, edx
	je L34
L33:
	jmp L36
L34:
	mov eax, 0
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L35:
	jmp prime_2_end
L36:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 2
	add eax, edx
	mov DWORD PTR [ebp - 28], eax
L37:
	mov eax, DWORD PTR [ebp - 28]
	mov DWORD PTR [ebp - 4], eax
L38:
	jmp L28
L39:
	mov eax, 1
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L40:
	jmp prime_2_end
L41:
prime_2_end:
	mov esp, ebp
	pop ebp
	ret
L42:
main:
	push ebp
	mov ebp, esp
	sub esp, 60
L43:
	mov esi, OFFSET FLAT:STR1
	push esi
L44:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L45:
	lea esi, DWORD PTR [ebp - 16]
	push esi
L46:
	push 0
	call geti_1
	add esp, 8
L47:
	mov eax, DWORD PTR [ebp - 16]
	mov DWORD PTR [ebp - 4], eax
L48:
	mov esi, OFFSET FLAT:STR2
	push esi
L49:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L50:
	mov eax, 0
	mov DWORD PTR [ebp - 12], eax
L51:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 2
	cmp eax, edx
	jge L53
L52:
	jmp L59
L53:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 20], eax
L54:
	mov eax, DWORD PTR [ebp - 20]
	mov DWORD PTR [ebp - 12], eax
L55:
	mov eax, 2
	push eax
L56:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L57:
	mov esi, OFFSET FLAT:STR3
	push esi
L58:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L59:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 3
	cmp eax, edx
	jge L61
L60:
	jmp L67
L61:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 24], eax
L62:
	mov eax, DWORD PTR [ebp - 24]
	mov DWORD PTR [ebp - 12], eax
L63:
	mov eax, 3
	push eax
L64:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L65:
	mov esi, OFFSET FLAT:STR3
	push esi
L66:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L67:
	mov eax, 6
	mov DWORD PTR [ebp - 8], eax
L68:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 4]
	cmp eax, edx
	jle L70
L69:
	jmp L101
L70:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 28], eax
L71:
	mov eax, DWORD PTR [ebp - 28]
	push eax
L72:
	lea esi, DWORD PTR [ebp - 32]
	push esi
L73:
	push ebp
	call prime_2
	add esp, 12
L74:
	mov eax, DWORD PTR [ebp - 32]
	mov edx, 1
	cmp eax, edx
	je L76
L75:
	jmp L83
L76:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 36], eax
L77:
	mov eax, DWORD PTR [ebp - 36]
	mov DWORD PTR [ebp - 12], eax
L78:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 40], eax
L79:
	mov eax, DWORD PTR [ebp - 40]
	push eax
L80:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L81:
	mov esi, OFFSET FLAT:STR3
	push esi
L82:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L83:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 4]
	cmp eax, edx
	jne L85
L84:
	jmp L98
L85:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 44], eax
L86:
	mov eax, DWORD PTR [ebp - 44]
	push eax
L87:
	lea esi, DWORD PTR [ebp - 48]
	push esi
L88:
	push ebp
	call prime_2
	add esp, 12
L89:
	mov eax, DWORD PTR [ebp - 48]
	mov edx, 1
	cmp eax, edx
	je L91
L90:
	jmp L98
L91:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 52], eax
L92:
	mov eax, DWORD PTR [ebp - 52]
	mov DWORD PTR [ebp - 12], eax
L93:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 56], eax
L94:
	mov eax, DWORD PTR [ebp - 56]
	push eax
L95:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L96:
	mov esi, OFFSET FLAT:STR3
	push esi
L97:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L98:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 6
	add eax, edx
	mov DWORD PTR [ebp - 60], eax
L99:
	mov eax, DWORD PTR [ebp - 60]
	mov DWORD PTR [ebp - 8], eax
L100:
	jmp L68
L101:
	mov esi, OFFSET FLAT:STR4
	push esi
L102:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L103:
	mov eax, DWORD PTR [ebp - 12]
	push eax
L104:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L105:
	mov esi, OFFSET FLAT:STR3
	push esi
L106:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L107:
main_1_end:
	mov esp, ebp
	pop ebp
	ret
	puts_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printStr
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	puti_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printInt
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	putc_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printChar
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	gets_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR stdin
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	mov eax, DWORD PTR [ebp + 16]
	push eax
	call fgets
	add esp, 12
	mov eax, 10 # Carriage return
	push eax
	mov eax, DWORD PTR [ebp + 16]
	push eax
	call strchr
	add esp, 8
	cmp eax, 0
	je grace_gets_no_newline
	mov BYTE PTR [eax], 0
	grace_gets_no_newline:
	mov esp, ebp
	pop ebp
	ret
	geti_1:
	push ebp
	mov ebp, esp
	sub esp, 4
	lea eax, DWORD PTR [ebp - 4]
	push eax
	mov eax, OFFSET FLAT:printInt
	push eax
	call scanf
	add esp, 8
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	getc_1:
	push ebp
	mov ebp, esp
	sub esp, 1
	lea eax, DWORD PTR [ebp - 4]
	push eax
	mov eax, OFFSET FLAT:printChar
	push eax
	call scanf
	add esp, 8
	mov eax, BYTE PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov BYTE PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	strlen_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	call strlen
	add esp, 4
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	strcmp_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	call strcmp
	add esp, 8
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	strcpy_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	call strcpy
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	strcat_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	call strcat
	add esp, 8
	mov esp, ebp
	pop ebp
	ret

.data
	STR3: .ASCIZ "\n"
	STR4: .ASCIZ "\nTotal: "
	STR1: .ASCIZ "Limit: "
	STR2: .ASCIZ "Primes:\n"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
