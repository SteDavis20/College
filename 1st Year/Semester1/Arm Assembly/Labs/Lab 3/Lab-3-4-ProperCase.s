;
; CSU11021 Introduction to Computing I 2019/2020
; Proper Case
;


; hello WORLD should become Hello World

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr	; address of existing string
	LDR	R1, =0x40000000	; address for new string
	
firstCharacter
	LDRB R2, [R0]		; load ascii value from address in memory of R0 into R2 
	CMP R2, #'a'		; if (firstCharacter >= a
	BLO updatePosition			; &&
	CMP R2, #'z'		; firstCharacter <= z) {
	BHI updatePosition
	SUB R2, R2, #('a' - 'A'); firstCharacter is capitalised )
updatePosition
	STRB R2, [R1]
	ADD R0, R0, #1		; change position of existing string
	ADD R1, R1, #1		; change position of new string

startWhileLoop
	LDRB R2, [R0]		; load value from address of R0 into register 
	CMP R2, #0x00		; while (currentCharacter != 0)
	BEQ endOfString
	CMP R2, #'A'		; if (currentCharacter >= A
	BLO spaceCheckLoop
	CMP R2, #'Z'		; && currentCharacter <= Z) {
	BHI universal
	ADD R2, R2, #('a' - 'A'); convert currentCharacter from uppercase to lowercase
	STRB R2, [R1]
	ADD R0, R0, #1		; change position of existing string
	ADD R1, R1, #1		; change position of new string
	B startWhileLoop
	
spaceCheckLoop
	;LDRB R2, [R0]		; load value from address into register
	CMP R2, #0x20		; if (currentCharacter == space
	BNE startWhileLoop
	
	STRB R2, [R1]		; store memory in R0 into R1
	ADD R0, R0, #1		; advance address/location by 1
	ADD R1, R1, #1		; advance address/location by 1
	
previousWasSpace
	LDRB R2, [R0]		; load value from address into register
	
	CMP R2, #'a'		; if (currentCharacter >= a 
	BLO universal			; 
	CMP R2, #'z'		; && currentCharacter <= z) {
	BHI spaceCheckLoop			;
	SUB R2, R2, #('a'-'A')	; currentCharacter = capitalLetter
	STRB R2, [R1]		; change value in memory
	ADD R0, R0, #1		; change position of existing string
	ADD R1, R1, #1		; change position of new string
	B startWhileLoop

universal
	STRB R2, [R1]
	ADD R0, R0, #1
	ADD R1, R1, #1
	B startWhileLoop	; }
		
endOfString
STOP	B	STOP

tststr	DCB	"HELLO world",0

	END
