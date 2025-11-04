const demoArea = document.getElementById('demoArea');
document.querySelectorAll('button[data-demo]').forEach(b=>b.addEventListener('click',()=>runDemo(b.dataset.demo)));

function runDemo(id){ demoArea.innerHTML = '';
  if(id==='binary'){
    demoArea.innerHTML = `<h3>Binary Search (recursive)</h3><div id='out'></div>`;
    const arr=[1,3,5,7,9,11,13,15,17,19]; const key=13;
    const idx = binarySearchRec(arr,0,arr.length-1,key);
    document.getElementById('out').innerText = `Array: ${arr}\nKey: ${key}\nFound at index: ${idx}`;
  }
  if(id==='sort'){
    demoArea.innerHTML = `<h3>Quick & Merge Sort</h3><pre id='out'></pre>`;
    const arr=[34,7,23,32,5,62]; const q=arr.slice(); quickSort(q,0,q.length-1);
    const m=arr.slice(); mergeSort(m,0,m.length-1);
    document.getElementById('out').innerText = `Original: ${arr}\nQuickSort: ${q}\nMergeSort: ${m}`;
  }
  if(id==='knap'){
    demoArea.innerHTML = `<h3>Fractional Knapsack (Greedy)</h3><pre id='out'></pre>`;
    const items=[{v:60,w:10},{v:100,w:20},{v:120,w:30}]; const W=50;
    document.getElementById('out').innerText = `Max value: ${fractionalKnapsack(items,W)}`;
  }
  if(id==='mst'){
    demoArea.innerHTML = `<h3>MST — Prim vs Kruskal</h3><pre id='out'></pre>`;
    const edges=[ [0,1,10],[0,2,6],[0,3,5],[1,3,15],[2,3,4] ];
    document.getElementById('out').innerText = `Kruskal: ${kruskal(4,edges)}\nPrim: ${prim(4,edges)}`;
  }
  if(id==='queen'){
    demoArea.innerHTML = `<h3>8-Queens (find one solution)</h3><pre id='out'></pre>`;
    const sol = solve8(); document.getElementById('out').innerText = sol.map(r=>r.map(c=>c?'Q':'.').join(' ')).join('\\n');
  }
}

// Algorithm implementations
function binarySearchRec(a,l,r,key){ if(l>r) return -1; const m = Math.floor((l+r)/2); if(a[m]===key) return m; if(key<a[m]) return binarySearchRec(a,l,m-1,key); return binarySearchRec(a,m+1,r,key); }
function quickSort(a,l,r){ if(l<r){ const p=partition(a,l,r); quickSort(a,l,p-1); quickSort(a,p+1,r); } }
function partition(a,l,r){ const pivot=a[r]; let i=l-1; for(let j=l;j<r;j++) if(a[j]<=pivot){ i++; [a[i],a[j]]=[a[j],a[i]]; } [a[i+1],a[r]]=[a[r],a[i+1]]; return i+1; }
function mergeSort(a,l,r){ if(l<r){ const m=Math.floor((l+r)/2); mergeSort(a,l,m); mergeSort(a,m+1,r); merge(a,l,m,r);} }
function merge(a,l,m,r){ const L=a.slice(l,m+1), R=a.slice(m+1,r+1); let i=0,j=0,k=l; while(i<L.length && j<R.length) a[k++]= (L[i]<=R[j])?L[i++]:R[j++]; while(i<L.length) a[k++]=L[i++]; while(j<R.length) a[k++]=R[j++]; }
function fractionalKnapsack(items,W){ items.sort((a,b)=> (b.v/b.w)-(a.v/a.w)); let val=0; for(let it of items){ if(W===0) break; if(it.w<=W){ val+=it.v; W-=it.w } else { val+=it.v*(W/it.w); W=0 } } return val }
function kruskal(V, edges){ edges = edges.slice().sort((a,b)=>a[2]-b[2]); const p = Array.from({length:V},(_,i)=>i); function find(x){ return p[x]==x?x:(p[x]=find(p[x])); }
 let total=0,cnt=0; for(let e of edges){ let a=find(e[0]), b=find(e[1]); if(a!=b){ p[a]=b; total+=e[2]; if(++cnt==V-1) break; } } return total; }
function prim(V, edges){ const adj=Array.from({length:V},()=>[]); for(let [u,v,w] of edges){ adj[u].push([v,w]); adj[v].push([u,w]); }
 const vis=Array(V).fill(false); const pq=[ [0,0] ]; let total=0; while(pq.length){ pq.sort((a,b)=>a[0]-b[0]); const [w,u]=pq.shift(); if(vis[u]) continue; vis[u]=true; total+=w; for(let [v,ww] of adj[u]) if(!vis[v]) pq.push([ww,v]); } return total; }
function solve8(){ const N=8; const board=Array(N).fill(-1); function ok(col,row){ for(let c=0;c<col;c++){ const r=board[c]; if(r===row) return false; if(Math.abs(r-row)===Math.abs(c-col)) return false; } return true }
 function place(col){ if(col===N) return true; for(let r=0;r<N;r++){ if(ok(col,r)){ board[col]=r; if(place(col+1)) return true; } } board[col]=-1; return false }
 place(0); const grid = Array.from({length:N},()=>Array(N).fill(0)); for(let c=0;c<N;c++) grid[board[c]][c]=1; return grid; }

runDemo('binary');
