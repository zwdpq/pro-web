function delFruit(fid){
    if (confirm('是否要删除该水果？')){
        window.location.href='fruit.do?fid='+fid+'&operate=delFruit';
    }
}

function page(pageNo){
    window.location.href='fruit.do?pageNo='+pageNo;
}