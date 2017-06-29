.intel_syntax noprefix
.text
	.global main
L1:

main:
	push ebp
	mov ebp, esp
	sub esp, 318
L2:
	lea esi, BYTE PTR [ebp - 305]
	push esi
L3:
	push 0
	call getc_1
	add esp, 8
L4:
	mov eax, BYTE PTR [ebp - 305]
	push eax
L5:
	lea esi, DWORD PTR [ebp - 309]
	push esi
L6:
	push 0
	call ord_1
	add esp, 12
L7:
	mov eax, DWORD PTR [ebp - 309]
	push eax
L8:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L9:
	lea esi, DWORD PTR [ebp - 313]
	push esi
L10:
	push 0
	call geti_1
	add esp, 8
L11:
	mov eax, DWORD PTR [ebp - 313]
	push eax
L12:
	lea esi, BYTE PTR [ebp - 314]
	push esi
L13:
	push 0
	call chr_1
	add esp, 12
L14:
	mov eax, BYTE PTR [ebp - 314]
	push eax
L15:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L16:
	jmp main_1_end
L17:
	mov eax, 100
	push eax
L18:
	lea esi, BYTE PTR [ebp - 104]
	push esi
L19:
	sub esp, 4
	push 0
	call gets_1
	add esp, 16
L20:
	mov eax, 100
	push eax
L21:
	lea esi, BYTE PTR [ebp - 204]
	push esi
L22:
	sub esp, 4
	push 0
	call gets_1
	add esp, 16
L23:
	lea esi, BYTE PTR [ebp - 304]
	push esi
L24:
	lea esi, BYTE PTR [ebp - 104]
	push esi
L25:
	sub esp, 4
	push 0
	call strcpy_1
	add esp, 16
L26:
	lea esi, BYTE PTR [ebp - 104]
	push esi
L27:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L28:
	lea esi, BYTE PTR [ebp - 304]
	push esi
L29:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L30:
	mov eax, '\n'
	push eax
L31:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L32:
	lea esi, BYTE PTR [ebp - 104]
	push esi
L33:
	lea esi, BYTE PTR [ebp - 204]
	push esi
L34:
	sub esp, 4
	push 0
	call strcat_1
	add esp, 16
L35:
	lea esi, BYTE PTR [ebp - 104]
	push esi
L36:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L37:
	jmp main_1_end
L38:
	lea esi, BYTE PTR [ebp - 104]
	push esi
L39:
	lea esi, DWORD PTR [ebp - 318]
	push esi
L40:
	push 0
	call strlen_1
	add esp, 12
L41:
	mov eax, DWORD PTR [ebp - 318]
	push eax
L42:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L43:

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
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
