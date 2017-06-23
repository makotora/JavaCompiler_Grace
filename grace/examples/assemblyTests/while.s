.intel_syntax noprefix
.text
	.global main
L1:
boo_2:
	push ebp
	mov ebp, esp
	sub esp, 0
L2:
boo_2_end:
	mov esp, ebp
	pop ebp
	ret
L3:
foo_2:
	push ebp
	mov ebp, esp
	sub esp, 4
L4:
	mov esi, OFFSET FLAT:STR1
	push esi
L5:
	sub esp, 4
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	push DWORD PTR [esi + 8]
	call puts_1
	add esp, 12
L6:
	mov eax, 0
	mov ecx, DWORD PTR [ebp + 20]
	add eax, ecx
	mov DWORD PTR [ebp - 4], eax
L7:
	mov eax, 'D'
	mov edi, DWORD PTR [ebp - 4]
	mov DWORD PTR [edi], eax
L8:
	mov esi, OFFSET FLAT:STR2
	push esi
L9:
	sub esp, 4
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	push DWORD PTR [esi + 8]
	call puts_1
	add esp, 12
L10:
foo_2_end:
	mov esp, ebp
	pop ebp
	ret
L11:
main:
	push ebp
	mov ebp, esp
	sub esp, 123
L12:
	mov eax, 0
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 39], eax
L13:
	mov eax, 'S'
	mov edi, DWORD PTR [ebp - 39]
	mov DWORD PTR [edi], eax
L14:
	mov eax, 1
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 43], eax
L15:
	mov eax, 'I'
	mov edi, DWORD PTR [ebp - 43]
	mov DWORD PTR [edi], eax
L16:
	mov eax, 2
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 47], eax
L17:
	mov eax, 'C'
	mov edi, DWORD PTR [ebp - 47]
	mov DWORD PTR [edi], eax
L18:
	mov eax, 3
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 51], eax
L19:
	mov eax, 'K'
	mov edi, DWORD PTR [ebp - 51]
	mov DWORD PTR [edi], eax
L20:
	mov eax, 4
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 55], eax
L21:
	mov eax, '\t'
	mov edi, DWORD PTR [ebp - 55]
	mov DWORD PTR [edi], eax
L22:
	mov eax, 5
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 59], eax
L23:
	mov eax, '.'
	mov edi, DWORD PTR [ebp - 59]
	mov DWORD PTR [edi], eax
L24:
	mov eax, 6
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 63], eax
L25:
	mov eax, '\n'
	mov edi, DWORD PTR [ebp - 63]
	mov DWORD PTR [edi], eax
L26:
	mov eax, 7
	lea ecx, DWORD PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 67], eax
L27:
	mov eax, 0
	mov edi, DWORD PTR [ebp - 67]
	mov DWORD PTR [edi], eax
L28:
	lea esi, DWORD PTR [ebp - 35]
	push esi
L29:
	lea esi, DWORD PTR [ebp - 4]
	push esi
L30:
	sub esp, 4
	push ebp
	call foo_2
	add esp, 16
L31:
	lea esi, DWORD PTR [ebp - 35]
	push esi
L32:
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call puts_1
	add esp, 12
L33:
	jmp main_1_end
L34:
	mov esi, OFFSET FLAT:STR3
	push esi
L35:
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call puts_1
	add esp, 12
L36:
	mov eax, 0
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 71], eax
L37:
	mov esi, DWORD PTR [ebp - 71]
	push esi
L38:
	mov eax, 0
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 75], eax
L39:
	mov edi, DWORD PTR [ebp - 75]
	mov eax, DWORD PTR [edi]
	push eax
L40:
	lea esi, DWORD PTR [ebp - 24]
	push esi
L41:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L42:
	sub esp, 4
	push ebp
	call boo_2
	add esp, 24
L43:
	mov eax, 0
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 79], eax
L44:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 83], eax
L45:
	mov eax, DWORD PTR [ebp - 83]
	mov edi, DWORD PTR [ebp - 79]
	mov DWORD PTR [edi], eax
L46:
	mov eax, 1
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 87], eax
L47:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 91], eax
L48:
	mov eax, DWORD PTR [ebp - 91]
	mov edi, DWORD PTR [ebp - 87]
	mov DWORD PTR [edi], eax
L49:
	mov eax, 2
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 95], eax
L50:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 99], eax
L51:
	mov eax, DWORD PTR [ebp - 99]
	mov edi, DWORD PTR [ebp - 95]
	mov DWORD PTR [edi], eax
L52:
	mov eax, 3
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 103], eax
L53:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 107], eax
L54:
	mov eax, DWORD PTR [ebp - 107]
	mov edi, DWORD PTR [ebp - 103]
	mov DWORD PTR [edi], eax
L55:
	mov eax, 4
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 111], eax
L56:
	mov eax, 1
	mov edi, DWORD PTR [ebp - 111]
	mov DWORD PTR [edi], eax
L57:
	mov eax, 2
	mov ecx, OFFSET FLAT:STR4
	add eax, ecx
	mov DWORD PTR [ebp - 115], eax
L58:
	mov edi, DWORD PTR [ebp - 115]
	mov eax, DWORD PTR [edi]
	mov BYTE PTR [ebp - 25], eax
L59:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L60:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 119], eax
L61:
	mov edi, DWORD PTR [ebp - 119]
	mov eax, DWORD PTR [edi]
	mov edx, 0
	cmp eax, edx
	jl L63
L62:
	jmp L66
L63:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 123], eax
L64:
	mov eax, DWORD PTR [ebp - 123]
	mov DWORD PTR [ebp - 4], eax
L65:
	jmp L60
L66:
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

.data
	STR4: .ASCIZ "A BIG STRIIIIING\t\n"
	STR2: .ASCIZ "there"
	STR3: .ASCIZ "after\n"
	STR1: .ASCIZ "here"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
