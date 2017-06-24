.intel_syntax noprefix
.text
	.global main
L1:
boo_3:
	push ebp
	mov ebp, esp
	sub esp, 172
L2:
	mov esi, OFFSET FLAT:STR1
	push esi
L3:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L4:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	push eax
L5:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L6:
	mov eax, '\n'
	push eax
L7:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L8:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 20
	cmp eax, edx
	jl L10
L9:
	jmp L45
L10:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 84], eax
L11:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 88], edx
L12:
	mov eax, DWORD PTR [ebp - 84]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 92], eax
L13:
	mov eax, DWORD PTR [ebp - 88]
	mov edx, DWORD PTR [ebp - 92]
	add eax, edx
	mov DWORD PTR [ebp - 96], eax
L14:
	mov eax, DWORD PTR [ebp - 96]
	mov ecx, 4
	imul ecx
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 100], eax
L15:
	mov edi, DWORD PTR [ebp - 100]
	mov eax, DWORD PTR [edi]
	push eax
L16:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L17:
	mov eax, ' '
	push eax
L18:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L19:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 104], eax
L20:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 108], edx
L21:
	mov eax, DWORD PTR [ebp - 104]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 112], eax
L22:
	mov eax, DWORD PTR [ebp - 108]
	mov edx, DWORD PTR [ebp - 112]
	add eax, edx
	mov DWORD PTR [ebp - 116], eax
L23:
	mov eax, DWORD PTR [ebp - 116]
	mov ecx, 4
	imul ecx
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 120], eax
L24:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 124], eax
L25:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 128], edx
L26:
	mov eax, DWORD PTR [ebp - 124]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 132], eax
L27:
	mov eax, DWORD PTR [ebp - 128]
	mov edx, DWORD PTR [ebp - 132]
	add eax, edx
	mov DWORD PTR [ebp - 136], eax
L28:
	mov eax, DWORD PTR [ebp - 136]
	mov ecx, 4
	imul ecx
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 140], eax
L29:
	mov edi, DWORD PTR [ebp - 140]
	mov eax, DWORD PTR [edi]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 144], eax
L30:
	mov eax, DWORD PTR [ebp - 144]
	mov edi, DWORD PTR [ebp - 120]
	mov DWORD PTR [edi], eax
L31:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 148], eax
L32:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 152], edx
L33:
	mov eax, DWORD PTR [ebp - 148]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 156], eax
L34:
	mov eax, DWORD PTR [ebp - 152]
	mov edx, DWORD PTR [ebp - 156]
	add eax, edx
	mov DWORD PTR [ebp - 160], eax
L35:
	mov eax, DWORD PTR [ebp - 160]
	mov ecx, 4
	imul ecx
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 164], eax
L36:
	mov edi, DWORD PTR [ebp - 164]
	mov eax, DWORD PTR [edi]
	push eax
L37:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L38:
	mov eax, '\n'
	push eax
L39:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L40:
	mov eax, '\n'
	push eax
L41:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L42:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 168], eax
L43:
	mov eax, DWORD PTR [ebp - 168]
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov DWORD PTR [esi - 4], eax
L44:
	jmp L8
L45:
	mov eax, 100
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov DWORD PTR [esi - 8], eax
L46:
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 16]
	push esi
L47:
	lea esi, DWORD PTR [ebp - 172]
	push esi
L48:
	mov esi, DWORD PTR [ebp + 8]
	push DWORD PTR [esi + 8]
	call foo_2
	add esp, 12
L49:
	mov eax, DWORD PTR [ebp - 172]
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov DWORD PTR [esi - 12], eax
L50:
	mov esi, OFFSET FLAT:STR2
	push esi
L51:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L52:
boo_3_end:
	mov esp, ebp
	pop ebp
	ret
L53:
foo_2:
	push ebp
	mov ebp, esp
	sub esp, 32
L54:
	mov esi, OFFSET FLAT:STR3
	push esi
L55:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L56:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	push eax
L57:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L58:
	mov eax, '\n'
	push eax
L59:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L60:
	mov esi, OFFSET FLAT:STR4
	push esi
L61:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L62:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 8]
	push eax
L63:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L64:
	mov eax, '\n'
	push eax
L65:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L66:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 0
	cmp eax, edx
	jg L68
L67:
	jmp L93
L68:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 4], eax
L69:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	mov DWORD PTR [esi - 4], eax
L70:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 8], eax
L71:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L72:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L73:
	mov eax, ' '
	push eax
L74:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L75:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 12], edx
L76:
	mov eax, DWORD PTR [ebp - 12]
	push eax
L77:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L78:
	mov eax, ' '
	push eax
L79:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L80:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 16], eax
L81:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 20], edx
L82:
	mov eax, DWORD PTR [ebp - 16]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 24], eax
L83:
	mov eax, DWORD PTR [ebp - 20]
	mov edx, DWORD PTR [ebp - 24]
	add eax, edx
	mov DWORD PTR [ebp - 28], eax
L84:
	mov eax, DWORD PTR [ebp - 28]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 32], eax
L85:
	mov edi, DWORD PTR [ebp - 32]
	mov eax, DWORD PTR [edi]
	push eax
L86:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L87:
	mov eax, '\n'
	push eax
L88:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L89:
	mov eax, '\n'
	push eax
L90:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L91:
	mov eax, 'z'
	mov esi, DWORD PTR [ebp + 8]
	mov BYTE PTR [esi - 93], eax
L92:
	jmp L66
L93:
	mov eax, '\n'
	push eax
L94:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L95:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 8]
	mov edx, 100
	cmp eax, edx
	jne L97
L96:
	jmp L104
L97:
	mov esi, OFFSET FLAT:STR5
	push esi
L98:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L99:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	push eax
L100:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L101:
	mov eax, '\n'
	push eax
L102:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L103:
	sub esp, 4
	push ebp
	call boo_3
	add esp, 8
L104:
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L105:
	jmp foo_2_end
L106:
foo_2_end:
	mov esp, ebp
	pop ebp
	ret
L107:
main:
	push ebp
	mov ebp, esp
	sub esp, 179
L108:
	mov esi, OFFSET FLAT:STR6
	push esi
L109:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L110:
	mov eax, 1
	mov edx, 24
	add eax, edx
	mov DWORD PTR [ebp - 107], eax
L111:
	mov eax, DWORD PTR [ebp - 107]
	push eax
L112:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L113:
	mov eax, '\n'
	push eax
L114:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L115:
	mov esi, OFFSET FLAT:STR7
	push esi
L116:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L117:
	mov eax, 51
	mov edx, 100
	sub eax, edx
	mov DWORD PTR [ebp - 111], eax
L118:
	mov eax, DWORD PTR [ebp - 111]
	push eax
L119:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L120:
	mov eax, '\n'
	push eax
L121:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L122:
	mov esi, OFFSET FLAT:STR8
	push esi
L123:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L124:
	mov eax, 5
	mov ecx, 12
	imul ecx
	mov DWORD PTR [ebp - 115], eax
L125:
	mov eax, DWORD PTR [ebp - 115]
	push eax
L126:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L127:
	mov eax, '\n'
	push eax
L128:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L129:
	mov esi, OFFSET FLAT:STR9
	push esi
L130:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L131:
	mov eax, 10
	cdq
	mov ebx, 3
	idiv ebx
	mov DWORD PTR [ebp - 119], eax
L132:
	mov eax, DWORD PTR [ebp - 119]
	push eax
L133:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L134:
	mov eax, '\n'
	push eax
L135:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L136:
	mov esi, OFFSET FLAT:STR10
	push esi
L137:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L138:
	mov eax, 5
	mov ecx, 6
	imul ecx
	mov DWORD PTR [ebp - 123], eax
L139:
	mov eax, DWORD PTR [ebp - 123]
	push eax
L140:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L141:
	mov eax, '\n'
	push eax
L142:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L143:
	mov eax, '\n'
	push eax
L144:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L145:
	mov eax, '\n'
	push eax
L146:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L147:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L148:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 20
	cmp eax, edx
	jl L150
L149:
	jmp L180
L150:
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 127], eax
L151:
	mov eax, DWORD PTR [ebp - 127]
	push eax
L152:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L153:
	mov eax, ' '
	push eax
L154:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L155:
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 131], edx
L156:
	mov eax, DWORD PTR [ebp - 131]
	push eax
L157:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L158:
	mov eax, ' '
	push eax
L159:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L160:
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 135], eax
L161:
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 139], edx
L162:
	mov eax, DWORD PTR [ebp - 135]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 143], eax
L163:
	mov eax, DWORD PTR [ebp - 139]
	mov edx, DWORD PTR [ebp - 143]
	add eax, edx
	mov DWORD PTR [ebp - 147], eax
L164:
	mov eax, DWORD PTR [ebp - 147]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 92]
	add eax, ecx
	mov DWORD PTR [ebp - 151], eax
L165:
	mov eax, DWORD PTR [ebp - 4]
	mov edi, DWORD PTR [ebp - 151]
	mov DWORD PTR [edi], eax
L166:
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 155], eax
L167:
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 159], edx
L168:
	mov eax, DWORD PTR [ebp - 155]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 163], eax
L169:
	mov eax, DWORD PTR [ebp - 159]
	mov edx, DWORD PTR [ebp - 163]
	add eax, edx
	mov DWORD PTR [ebp - 167], eax
L170:
	mov eax, DWORD PTR [ebp - 167]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 92]
	add eax, ecx
	mov DWORD PTR [ebp - 171], eax
L171:
	mov edi, DWORD PTR [ebp - 171]
	mov eax, DWORD PTR [edi]
	push eax
L172:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L173:
	mov eax, '\n'
	push eax
L174:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L175:
	mov eax, '\n'
	push eax
L176:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L177:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 175], eax
L178:
	mov eax, DWORD PTR [ebp - 175]
	mov DWORD PTR [ebp - 4], eax
L179:
	jmp L148
L180:
	mov eax, 'a'
	mov BYTE PTR [ebp - 93], eax
L181:
	mov eax, BYTE PTR [ebp - 93]
	push eax
L182:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L183:
	mov eax, '\n'
	push eax
L184:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L185:
	mov esi, OFFSET FLAT:STR11
	push esi
L186:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L187:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L188:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L189:
	mov eax, '\n'
	push eax
L190:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L191:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L192:
	lea esi, DWORD PTR [ebp - 92]
	push esi
L193:
	lea esi, DWORD PTR [ebp - 179]
	push esi
L194:
	push ebp
	call foo_2
	add esp, 12
L195:
	mov eax, DWORD PTR [ebp - 179]
	mov DWORD PTR [ebp - 12], eax
L196:
	mov esi, OFFSET FLAT:STR12
	push esi
L197:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L198:
	mov eax, DWORD PTR [ebp - 12]
	push eax
L199:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L200:
	mov eax, '\n'
	push eax
L201:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L202:
	mov esi, OFFSET FLAT:STR13
	push esi
L203:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L204:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L205:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L206:
	mov eax, '\n'
	push eax
L207:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L208:
	mov eax, BYTE PTR [ebp - 93]
	push eax
L209:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L210:
	mov eax, '\n'
	push eax
L211:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L212:
	mov eax, '\n'
	push eax
L213:
	sub esp, 4
	push 0
	call putc_1
	add esp, 12
L214:
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

.data
	STR9: .ASCIZ "10 div 3 = "
	STR13: .ASCIZ "i is : "
	STR7: .ASCIZ "51 - 100 = "
	STR10: .ASCIZ "5 * 6 = "
	STR8: .ASCIZ "5 * 12 = "
	STR12: .ASCIZ "Foo returned : "
	STR6: .ASCIZ "1 + 24 = "
	STR4: .ASCIZ "In foo function with j: "
	STR11: .ASCIZ "Calling foo with i: "
	STR2: .ASCIZ "Here\n"
	STR5: .ASCIZ "Calling boo with i: "
	STR3: .ASCIZ "In foo function with i: "
	STR1: .ASCIZ "In boo function with i: "
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
