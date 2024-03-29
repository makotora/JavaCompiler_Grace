.intel_syntax noprefix
.text
	.global main
L1:

swap_3:
	push ebp
	mov ebp, esp
	sub esp, 4
L2:
	mov esi, DWORD PTR [ebp + 20]
	mov eax, DWORD PTR [esi]
	mov DWORD PTR [ebp - 4], eax
L3:
	mov esi, DWORD PTR [ebp + 16]
	mov eax, DWORD PTR [esi]
	mov esi, DWORD PTR [ebp + 20]
	mov DWORD PTR [esi], eax
L4:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 16]
	mov DWORD PTR [esi], eax
L5:

swap_3_end:
	mov esp, ebp
	pop ebp
	ret
L6:

bsort_2:
	push ebp
	mov ebp, esp
	sub esp, 40
L7:
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax
L8:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L10
L9:
	jmp L31
L10:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L11:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L12:
	mov eax, DWORD PTR [ebp + 20]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 12], eax
L13:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 12]
	cmp eax, edx
	jl L15
L14:
	jmp L8
L15:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 16], edx
L16:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 20], eax
L17:
	mov eax, DWORD PTR [ebp - 20]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 24], edx
L18:
	mov edi, DWORD PTR [ebp - 16]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 24]
	mov edx, DWORD PTR [edi]
	cmp eax, edx
	jg L20
L19:
	jmp L27
L20:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 28], edx
L21:
	mov esi, DWORD PTR [ebp - 28]
	push esi
L22:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax
L23:
	mov eax, DWORD PTR [ebp - 32]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 36], edx
L24:
	mov esi, DWORD PTR [ebp - 36]
	push esi
L25:
	sub esp, 4
	push ebp
	call swap_3
	add esp, 16
L26:
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax
L27:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 40], eax
L28:
	mov eax, DWORD PTR [ebp - 40]
	mov DWORD PTR [ebp - 8], eax
L29:
	jmp L12
L30:
L31:

bsort_2_end:
	mov esp, ebp
	pop ebp
	ret
L32:

putArray_2:
	push ebp
	mov ebp, esp
	sub esp, 12
L33:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L34:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L35:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L36:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp + 20]
	cmp eax, edx
	jl L38
L37:
	jmp L48
L38:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L40
L39:
	jmp L42
L40:
	mov esi, OFFSET FLAT:STR1
	push esi
L41:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L42:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 8], edx
L43:
	mov edi, DWORD PTR [ebp - 8]
	mov eax, DWORD PTR [edi]
	push eax
L44:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L45:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 12], eax
L46:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 4], eax
L47:
	jmp L36
L48:
	mov esi, OFFSET FLAT:STR2
	push esi
L49:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L50:

putArray_2_end:
	mov esp, ebp
	pop ebp
	ret
L51:

main:
	push ebp
	mov ebp, esp
	sub esp, 96
L52:
	mov eax, 65
	mov DWORD PTR [ebp - 4], eax
L53:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L54:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 16
	cmp eax, edx
	jl L56
L55:
	jmp L66
L56:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 137
	imul ecx
	mov DWORD PTR [ebp - 76], eax
L57:
	mov eax, DWORD PTR [ebp - 76]
	mov edx, 220
	add eax, edx
	mov DWORD PTR [ebp - 80], eax
L58:
	mov eax, DWORD PTR [ebp - 80]
	mov edx, DWORD PTR [ebp - 8]
	add eax, edx
	mov DWORD PTR [ebp - 84], eax
L59:
	mov eax, DWORD PTR [ebp - 84]
	cdq
	mov ebx, 101
	idiv ebx
	mov DWORD PTR [ebp - 88], edx
L60:
	mov eax, DWORD PTR [ebp - 88]
	mov DWORD PTR [ebp - 4], eax
L61:
	mov eax, DWORD PTR [ebp - 8]
	lea ecx, DWORD PTR [ebp - 72]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 92], edx
L62:
	mov eax, DWORD PTR [ebp - 88]
	mov edi, DWORD PTR [ebp - 92]
	mov DWORD PTR [edi], eax
L63:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 96], eax
L64:
	mov eax, DWORD PTR [ebp - 96]
	mov DWORD PTR [ebp - 8], eax
L65:
	jmp L54
L66:
	mov esi, OFFSET FLAT:STR3
	push esi
L67:
	mov eax, 16
	push eax
L68:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L69:
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20
L70:
	mov eax, 16
	push eax
L71:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L72:
	sub esp, 4
	push ebp
	call bsort_2
	add esp, 16
L73:
	mov esi, OFFSET FLAT:STR4
	push esi
L74:
	mov eax, 16
	push eax
L75:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L76:
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20
L77:

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
	STR4: .ASCIZ "Sorted array: "
	STR2: .ASCIZ "\n"
	STR3: .ASCIZ "Initial array: "
	STR1: .ASCIZ ", "
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
