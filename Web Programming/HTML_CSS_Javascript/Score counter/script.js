

const player1 = document.getElementById("points1");
const player2 = document.getElementById("points2");

const button1 = document.getElementById("+1_P1");
const button2 = document.getElementById("+1_P2");

let score1 = 0;
let score2 = 0;

function incScore(player, score) {
    if (score <5) {
        return function() { 
            score++;
            player.textContent = score;
        
            if (score>=5) {
                if (player===player1) {
                    document.getElementById("Winner1").style.display = "inline";
                }
                else {
                    document.getElementById("Winner2").style.display = "inline";
                }
                button1.disabled = true;
                button2.disabled = true;
            }  
        };
    }
}

button1.addEventListener("click", incScore(player1, score1) );
button2.addEventListener("click", incScore(player2, score2) );

/* ORRRRRRRRRRRRRRR

    const player1 = document.getElementById("points1");
    const player2 = document.getElementById("points2");

    let score1 = 0;
    let score2 = 0;

    document.getElementById("+1_P1").addEventListener("click", function() {
        score1++;
        player1.textContent = score1;

        if (score1>=5) {
                document.getElementById("Winner1").style.display = "inline";
                button1.disabled = true;
                button2.disabled = true;
        }  
    })

    document.getElementById("+1_P2").addEventListener("click", function() {
        score2++;
        player2.textContent = score2;

        if (score2>=5) {
                document.getElementById("Winner2").style.display = "inline";
                button1.disabled = true;
                button2.disabled = true;
        }  
    })


*/