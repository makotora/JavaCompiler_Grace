.intel_syntax noprefix
.text
	.global main
L1:
foo_2:
	push ebp
	mov ebp, esp
	sub esp, 4
L2:
	mov eax, 0
	mov ecx, DWORD PTR [ebp + 20]
	add eax, ecx
	mov DWORD PTR [ebp - 4], eax
L3:
	mov eax, 'D'
	mov edi, DWORD PTR [ebp - 4]
	mov BYTE PTR [edi], eax
L4:
foo_2_end:
	mov esp, ebp
	pop ebp
	ret
L5:
main:
	push ebp
	mov ebp, esp
	sub esp, 227
L6:
	mov eax, 1
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 59], eax
L7:
	mov eax, 'S'
	mov edi, DWORD PTR [ebp - 59]
	mov BYTE PTR [edi], eax
L8:
	mov eax, 0
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 63], eax
L9:
	mov eax, 1
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 67], eax
L10:
	mov edi, DWORD PTR [ebp - 67]
	mov eax, BYTE PTR [edi]
	mov edi, DWORD PTR [ebp - 63]
	mov BYTE PTR [edi], eax
L11:
	mov eax, 1
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 71], eax
L12:
	mov eax, 'I'
	mov edi, DWORD PTR [ebp - 71]
	mov BYTE PTR [edi], eax
L13:
	mov eax, 2
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 75], eax
L14:
	mov eax, 'C'
	mov edi, DWORD PTR [ebp - 75]
	mov BYTE PTR [edi], eax
L15:
	mov eax, 3
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 79], eax
L16:
	mov eax, 'K'
	mov edi, DWORD PTR [ebp - 79]
	mov BYTE PTR [edi], eax
L17:
	mov eax, 4
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 83], eax
L18:
	mov eax, '\t'
	mov edi, DWORD PTR [ebp - 83]
	mov BYTE PTR [edi], eax
L19:
	mov eax, 5
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 87], eax
L20:
	mov eax, '.'
	mov edi, DWORD PTR [ebp - 87]
	mov BYTE PTR [edi], eax
L21:
	mov eax, 6
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 91], eax
L22:
	mov eax, '\n'
	mov edi, DWORD PTR [ebp - 91]
	mov BYTE PTR [edi], eax
L23:
	mov eax, 7
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 95], eax
L24:
	mov eax, 0
	mov edi, DWORD PTR [ebp - 95]
	mov BYTE PTR [edi], eax
L25:
	mov esi, OFFSET FLAT:STR1
	push esi
L26:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L27:
	lea esi, BYTE PTR [ebp - 35]
	push esi
L28:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L29:
	lea esi, BYTE PTR [ebp - 35]
	push esi
L30:
	lea esi, DWORD PTR [ebp - 4]
	push esi
L31:
	sub esp, 4
	push ebp
	call foo_2
	add esp, 16
L32:
	mov esi, OFFSET FLAT:STR2
	push esi
L33:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L34:
	lea esi, BYTE PTR [ebp - 35]
	push esi
L35:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L36:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 99], ecx
L37:
	mov eax, 1
	mov edx, DWORD PTR [ebp - 99]
	add eax, edx
	mov DWORD PTR [ebp - 103], eax
L38:
	mov eax, DWORD PTR [ebp - 103]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 107], eax
L39:
	mov eax, 'S'
	mov edi, DWORD PTR [ebp - 107]
	mov BYTE PTR [edi], eax
L40:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 111], ecx
L41:
	mov eax, 0
	mov edx, DWORD PTR [ebp - 111]
	add eax, edx
	mov DWORD PTR [ebp - 115], eax
L42:
	mov eax, DWORD PTR [ebp - 115]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 119], eax
L43:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 123], ecx
L44:
	mov eax, 1
	mov edx, DWORD PTR [ebp - 123]
	add eax, edx
	mov DWORD PTR [ebp - 127], eax
L45:
	mov eax, DWORD PTR [ebp - 127]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 131], eax
L46:
	mov edi, DWORD PTR [ebp - 131]
	mov eax, BYTE PTR [edi]
	mov edi, DWORD PTR [ebp - 119]
	mov BYTE PTR [edi], eax
L47:
	mov esi, OFFSET FLAT:STR3
	push esi
L48:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L49:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 135], ecx
L50:
	mov eax, 1
	mov edx, DWORD PTR [ebp - 135]
	add eax, edx
	mov DWORD PTR [ebp - 139], eax
L51:
	mov eax, DWORD PTR [ebp - 139]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 143], eax
L52:
	mov eax, 'I'
	mov edi, DWORD PTR [ebp - 143]
	mov BYTE PTR [edi], eax
L53:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 147], ecx
L54:
	mov eax, 2
	mov edx, DWORD PTR [ebp - 147]
	add eax, edx
	mov DWORD PTR [ebp - 151], eax
L55:
	mov eax, DWORD PTR [ebp - 151]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 155], eax
L56:
	mov eax, 'C'
	mov edi, DWORD PTR [ebp - 155]
	mov BYTE PTR [edi], eax
L57:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 159], ecx
L58:
	mov eax, 3
	mov edx, DWORD PTR [ebp - 159]
	add eax, edx
	mov DWORD PTR [ebp - 163], eax
L59:
	mov eax, DWORD PTR [ebp - 163]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 167], eax
L60:
	mov eax, 'K'
	mov edi, DWORD PTR [ebp - 167]
	mov BYTE PTR [edi], eax
L61:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 171], ecx
L62:
	mov eax, 4
	mov edx, DWORD PTR [ebp - 171]
	add eax, edx
	mov DWORD PTR [ebp - 175], eax
L63:
	mov eax, DWORD PTR [ebp - 175]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 179], eax
L64:
	mov eax, '\n'
	mov edi, DWORD PTR [ebp - 179]
	mov BYTE PTR [edi], eax
L65:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 183], ecx
L66:
	mov eax, 5
	mov edx, DWORD PTR [ebp - 183]
	add eax, edx
	mov DWORD PTR [ebp - 187], eax
L67:
	mov eax, DWORD PTR [ebp - 187]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 191], eax
L68:
	mov eax, 0
	mov edi, DWORD PTR [ebp - 191]
	mov BYTE PTR [edi], eax
L69:
	mov esi, OFFSET FLAT:STR1
	push esi
L70:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L71:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 199], ecx
L72:
	mov eax, DWORD PTR [ebp - 199]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 203], eax
L73:
	mov esi, DWORD PTR [ebp - 203]
	push esi
L74:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L75:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 211], ecx
L76:
	mov eax, DWORD PTR [ebp - 211]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 215], eax
L77:
	mov esi, DWORD PTR [ebp - 215]
	push esi
L78:
	lea esi, DWORD PTR [ebp - 4]
	push esi
L79:
	sub esp, 4
	push ebp
	call foo_2
	add esp, 16
L80:
	mov esi, OFFSET FLAT:STR2
	push esi
L81:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L82:
	mov eax, 1
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 223], ecx
L83:
	mov eax, DWORD PTR [ebp - 223]
	lea ecx, BYTE PTR [ebp - 55]
	add eax, ecx
	mov DWORD PTR [ebp - 227], eax
L84:
	mov esi, DWORD PTR [ebp - 227]
	push esi
L85:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L86:
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
	STR3: .ASCIZ "Now trying passing a sub array to foo\n"
	STR1: .ASCIZ "before\n"
	STR2: .ASCIZ "after\n"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
