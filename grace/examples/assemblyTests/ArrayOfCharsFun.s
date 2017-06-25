.intel_syntax noprefix
.text
	.global main
L1:

addToString_2:
	push ebp
	mov ebp, esp
	sub esp, 29
L2:
	mov esi, DWORD PTR [ebp + 16]
	push esi
L3:
	lea esi, DWORD PTR [ebp - 8]
	push esi
L4:
	push 0
	call strlen_1
	add esp, 12
L5:
	mov eax, DWORD PTR [ebp - 8]
	mov esi, DWORD PTR [ebp + 8]
	mov DWORD PTR [esi - 8], eax
L6:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L7:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	mov edx, DWORD PTR [esi - 8]
	cmp eax, edx
	jl L9
L8:
	jmp L24
L9:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 12], eax
L10:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 16], eax
L11:
	mov edi, DWORD PTR [ebp - 16]
	mov eax, BYTE PTR [edi]
	push eax
L12:
	lea esi, DWORD PTR [ebp - 20]
	push esi
L13:
	push 0
	call ord_1
	add esp, 12
L14:
	mov eax, DWORD PTR [ebp - 20]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 24], eax
L15:
	mov eax, DWORD PTR [ebp - 24]
	push eax
L16:
	lea esi, BYTE PTR [ebp - 25]
	push esi
L17:
	push 0
	call chr_1
	add esp, 12
L18:
	mov eax, BYTE PTR [ebp - 25]
	mov edi, DWORD PTR [ebp - 12]
	mov BYTE PTR [edi], eax
L19:
	mov esi, OFFSET FLAT:STR1
	push esi
L20:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L21:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 29], eax
L22:
	mov eax, DWORD PTR [ebp - 29]
	mov DWORD PTR [ebp - 4], eax
L23:
	jmp L7
L24:

addToString_2_end:
	mov esp, ebp
	pop ebp
	ret
L25:

main:
	push ebp
	mov ebp, esp
	sub esp, 180
L26:
	mov esi, OFFSET FLAT:STR2
	push esi
L27:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L28:
	mov esi, OFFSET FLAT:STR3
	push esi
L29:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L30:
	mov eax, 20
	push eax
L31:
	lea esi, BYTE PTR [ebp - 28]
	push esi
L32:
	sub esp, 4
	push 0
	call gets_1
	add esp, 16
L33:
	mov esi, OFFSET FLAT:STR4
	push esi
L34:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L35:
	lea esi, BYTE PTR [ebp - 28]
	push esi
L36:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L37:
	mov eax, '\n'
	push eax
L38:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L39:
	lea esi, BYTE PTR [ebp - 28]
	push esi
L40:
	sub esp, 4
	push ebp
	call addToString_2
	add esp, 12
L41:
	mov esi, OFFSET FLAT:STR5
	push esi
L42:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L43:
	lea esi, BYTE PTR [ebp - 28]
	push esi
L44:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L45:
	mov eax, '\n'
	push eax
L46:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L47:
	mov esi, OFFSET FLAT:STR6
	push esi
L48:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L49:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L50:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L51:
	mov eax, '\n'
	push eax
L52:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L53:
	mov esi, OFFSET FLAT:STR7
	push esi
L54:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L55:
L56:
L57:
	mov eax, 11
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 60], eax
L58:
	mov eax, 'A'
	mov edi, DWORD PTR [ebp - 60]
	mov BYTE PTR [edi], eax
L59:
L60:
L61:
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 72], eax
L62:
L63:
L64:
	mov eax, 11
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 84], eax
L65:
	mov edi, DWORD PTR [ebp - 84]
	mov eax, BYTE PTR [edi]
	mov edi, DWORD PTR [ebp - 72]
	mov BYTE PTR [edi], eax
L66:
L67:
L68:
	mov eax, 11
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 96], eax
L69:
	mov eax, 'B'
	mov edi, DWORD PTR [ebp - 96]
	mov BYTE PTR [edi], eax
L70:
L71:
L72:
	mov eax, 12
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 108], eax
L73:
	mov eax, 'C'
	mov edi, DWORD PTR [ebp - 108]
	mov BYTE PTR [edi], eax
L74:
L75:
L76:
	mov eax, 13
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 120], eax
L77:
	mov eax, 'D'
	mov edi, DWORD PTR [ebp - 120]
	mov BYTE PTR [edi], eax
L78:
L79:
L80:
	mov eax, 14
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 132], eax
L81:
	mov eax, '\n'
	mov edi, DWORD PTR [ebp - 132]
	mov BYTE PTR [edi], eax
L82:
L83:
L84:
	mov eax, 15
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 144], eax
L85:
	mov eax, 0
	mov edi, DWORD PTR [ebp - 144]
	mov BYTE PTR [edi], eax
L86:
	mov esi, OFFSET FLAT:STR4
	push esi
L87:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L88:
L89:
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 156], eax
L90:
	mov esi, DWORD PTR [ebp - 156]
	push esi
L91:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L92:
	mov eax, '\n'
	push eax
L93:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L94:
L95:
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 168], eax
L96:
	mov esi, DWORD PTR [ebp - 168]
	push esi
L97:
	sub esp, 4
	push ebp
	call addToString_2
	add esp, 12
L98:
	mov esi, OFFSET FLAT:STR5
	push esi
L99:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L100:
L101:
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	add eax, ecx
	mov DWORD PTR [ebp - 180], eax
L102:
	mov esi, DWORD PTR [ebp - 180]
	push esi
L103:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L104:
	mov eax, '\n'
	push eax
L105:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L106:

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

abs_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	cdq
	xor eax, edx
	sub eax, edx
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret

ord_1:
	push ebp
	mov ebp, esp
	mov eax, BYTE PTR [ebp + 16]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret

chr_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
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
	STR3: .ASCIZ "Insert a string (max 20 chars) : "
	STR1: .ASCIZ "Here\n"
	STR6: .ASCIZ "String\'s size is: "
	STR4: .ASCIZ "before: "
	STR7: .ASCIZ "\nNow passing ABCD from an array of strings!\n"
	STR2: .ASCIZ "This program adds 1 to each char of a given string\n"
	STR5: .ASCIZ "after: "
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
